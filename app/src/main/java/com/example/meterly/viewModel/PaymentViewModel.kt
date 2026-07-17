package com.example.meterly.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meterly.model.Payment
import com.example.meterly.model.UtilityType
import com.example.meterly.repository.PaymentRepository
import com.example.meterly.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import kotlin.math.roundToInt

class PaymentViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()
    private val paymentRepository = PaymentRepository()

    private val _currentPayments = MutableStateFlow<Map<UtilityType, Payment?>>(emptyMap())
    val currentPayments: StateFlow<Map<UtilityType, Payment?>> = _currentPayments

    private val _previousPayments = MutableStateFlow<Map<UtilityType, Payment?>>(emptyMap())
    val previousPayments: StateFlow<Map<UtilityType, Payment?>> = _previousPayments

    private val _selectedAnalyticsMonth = MutableStateFlow(currentMonth())
    val selectedAnalyticsMonth: StateFlow<Int> = _selectedAnalyticsMonth

    private val _selectedAnalyticsYear = MutableStateFlow(currentYear())
    val selectedAnalyticsYear: StateFlow<Int> = _selectedAnalyticsYear

    private val _analyticsPayments = MutableStateFlow<Map<UtilityType, Payment?>>(emptyMap())
    val analyticsPayments: StateFlow<Map<UtilityType, Payment?>> = _analyticsPayments

    private val _allPaymentsByType = MutableStateFlow<Map<UtilityType, List<Payment>>>(emptyMap())
    val allPaymentsByType: StateFlow<Map<UtilityType, List<Payment>>> = _allPaymentsByType

    private var currentAddressId: String? = null

    init {
        profileRepository.observeProfile { profileState ->
            val newAddressId = profileState.currentAddress?.id

            if (newAddressId == null) {
                currentAddressId = null
                paymentRepository.removeListener()
                _currentPayments.value = emptyMap()
                _previousPayments.value = emptyMap()
            } else if (newAddressId != currentAddressId) {
                currentAddressId = newAddressId
                paymentRepository.removeListener()
                observePayments(newAddressId)
            }
        }
    }

    private fun observePayments(addressId: String) {
        paymentRepository.observePayments(addressId) { payments ->
            val byType = payments
                .filter { payment ->
                    try {
                        UtilityType.valueOf(payment.utilityType)
                        true
                    } catch (_: IllegalArgumentException) {
                        false
                    }
                }
                .groupBy { UtilityType.valueOf(it.utilityType) }

            val current = mutableMapOf<UtilityType, Payment?>()
            val previous = mutableMapOf<UtilityType, Payment?>()

            val calendar = Calendar.getInstance()
            val curMonth = calendar.get(Calendar.MONTH) + 1
            val curYear = calendar.get(Calendar.YEAR)

            calendar.add(Calendar.MONTH, -1)
            val prevMonth = calendar.get(Calendar.MONTH) + 1
            val prevYear = calendar.get(Calendar.YEAR)

            UtilityType.entries.forEach { type ->
                val typePayments = byType[type] ?: emptyList()

                current[type] = typePayments.firstOrNull {
                    it.month == curMonth && it.year == curYear
                } ?: typePayments.maxByOrNull { it.date }

                previous[type] = typePayments.firstOrNull {
                    it.month == prevMonth && it.year == prevYear
                } ?: typePayments.sortedByDescending { it.date }.getOrNull(1)
            }

            _currentPayments.value = current
            _previousPayments.value = previous

            _allPaymentsByType.value = byType

            updateAnalyticsPayments()
        }
    }

    private fun updateAnalyticsPayments() {
        val month = _selectedAnalyticsMonth.value
        val year = _selectedAnalyticsYear.value
        val byType = _allPaymentsByType.value

        val result = mutableMapOf<UtilityType, Payment?>()
        UtilityType.entries.forEach { type ->
            val typePayments = byType[type] ?: emptyList()
            result[type] = typePayments.firstOrNull {
                it.month == month && it.year == year
            }
        }
        _analyticsPayments.value = result
    }

    fun selectAnalyticsPeriod(month: Int, year: Int) {
        _selectedAnalyticsMonth.value = month
        _selectedAnalyticsYear.value = year
        updateAnalyticsPayments()
    }

    private fun currentMonth(): Int {
        return Calendar.getInstance().get(Calendar.MONTH) + 1
    }

    private fun currentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    fun calculate(
        utility: UtilityType,
        begin: Double,
        end: Double,
        rate: Double
    ) {
        val addressId = currentAddressId ?: return
        val consumption = end - begin
        val amountDue = (consumption * rate * 100).roundToInt() / 100.0
        val now = System.currentTimeMillis()
        val calendar = Calendar.getInstance().apply { timeInMillis = now }

        val payment = Payment(
            utilityType = utility.name,
            monthBegin = begin,
            monthEnd = end,
            consumption = consumption,
            rate = rate,
            amountDue = amountDue,
            date = now,
            month = calendar.get(Calendar.MONTH) + 1,
            year = calendar.get(Calendar.YEAR),
            addressId = addressId
        )

        viewModelScope.launch {
            val savedId = paymentRepository.savePayment(payment)
            if (savedId.isNotEmpty()) {
                val savedPayment = payment.copy(id = savedId)
                val updated = _currentPayments.value.toMutableMap()
                updated[utility] = savedPayment
                _currentPayments.value = updated
            }
        }
    }

    fun togglePaid(utility: UtilityType, isPaid: Boolean) {
        val payment = _currentPayments.value[utility] ?: return
        if (payment.addressId.isEmpty()) return

        val updatedPayment = payment.copy(isPaid = isPaid)
        val updatedMap = _currentPayments.value.toMutableMap()
        updatedMap[utility] = updatedPayment
        _currentPayments.value = updatedMap

        viewModelScope.launch {
            try {
                if (payment.id.isEmpty()) {
                    val newId = paymentRepository.savePayment(updatedPayment)

                    val finalMap = _currentPayments.value.toMutableMap()
                    finalMap[utility] = updatedPayment.copy(id = newId)
                    _currentPayments.value = finalMap
                } else {
                    paymentRepository.updatePaidStatus(
                        addressId = payment.addressId,
                        paymentId = payment.id,
                        isPaid = isPaid
                    )
                }
            } catch (e: Exception) {
                val rollbackMap = _currentPayments.value.toMutableMap()
                rollbackMap[utility] = payment
                _currentPayments.value = rollbackMap
            }
        }
    }

    fun attachReceipt(
        utility: UtilityType,
        uri: String,
        fileName: String
    ) {
        val payment = _currentPayments.value[utility]
        if (payment == null) return
        if (payment.id.isEmpty()) return

        viewModelScope.launch {
            try {
                paymentRepository.updateReceipt(
                    addressId = payment.addressId,
                    paymentId = payment.id,
                    uri = uri,
                    fileName = fileName
                )
            } catch (_: Exception) { }
        }
    }

    fun getPreviousPayment(utility: UtilityType): Payment? {
        return _previousPayments.value[utility]
    }

    override fun onCleared() {
        paymentRepository.removeListener()
        profileRepository.removeListener()
        super.onCleared()
    }
}
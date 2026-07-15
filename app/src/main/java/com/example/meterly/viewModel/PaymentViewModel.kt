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
            val currentMonth = calendar.get(Calendar.MONTH) + 1
            val currentYear = calendar.get(Calendar.YEAR)

            calendar.add(Calendar.MONTH, -1)
            val prevMonth = calendar.get(Calendar.MONTH) + 1
            val prevYear = calendar.get(Calendar.YEAR)

            UtilityType.entries.forEach { type ->
                val typePayments = byType[type] ?: emptyList()

                current[type] = typePayments.firstOrNull {
                    it.month == currentMonth && it.year == currentYear
                } ?: typePayments.maxByOrNull { it.date }

                previous[type] = typePayments.firstOrNull {
                    it.month == prevMonth && it.year == prevYear
                } ?: typePayments.sortedByDescending { it.date }.getOrNull(1)
            }

            _currentPayments.value = current
            _previousPayments.value = previous
        }
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
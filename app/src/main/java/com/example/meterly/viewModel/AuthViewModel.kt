package com.example.meterly.viewModel

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meterly.repository.AuthRepository
import com.example.meterly.state.AuthState
import com.example.meterly.util.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()
    val authState: StateFlow<AuthState> = repository.authState
    var pendingFullName: String = ""
    var pendingAddress: String = ""
    private var pendingPhone: String = ""
    private var isRegisterFlow = false
    private val _registerError = MutableStateFlow<String?>(null)
    val registerError: StateFlow<String?> = _registerError
    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn: StateFlow<Boolean?> = _isLoggedIn

    init {
        repository.observeAuthState { loggedIn ->
            _isLoggedIn.value = loggedIn
        }
    }

    fun register(
        fullName: String,
        address: String,
        phone: String,
        activity: Activity
    ) {

        when {
            !Validator.isValidFullName(fullName) -> {
                _registerError.value = "Введіть повне ПІБ"
                return
            }

            !Validator.isValidAddress(address) -> {
                _registerError.value = "Некоректна адреса"
                return
            }

            !Validator.isValidPhone(phone) -> {
                _registerError.value = "Некоректний номер телефону"
                return
            }
        }

        val normalizedPhone = Validator.normalizePhone(phone)

        viewModelScope.launch {
            val nameExists = repository.checkFullNameExists(fullName)

            if (nameExists) {
                _registerError.value = "Користувач з таким ПІБ вже зареєстрований"

                return@launch
            }

            val phoneExists = repository.checkUserExists(normalizedPhone)

            if (phoneExists) {
                _registerError.value = "Користувач з таким номером вже зареєстрований"

                return@launch
            }

            _registerError.value = null

            pendingFullName = fullName
            pendingAddress = address
            pendingPhone = normalizedPhone

            isRegisterFlow = true

            repository.sendVerificationCode(
                normalizedPhone,
                activity
            )
        }
    }

    fun login(
        address: String,
        phone: String,
        activity: Activity,
        onUserNotFound: () -> Unit
    ) {

        if (!Validator.isValidPhone(phone)) {
            return
        }

        if (!Validator.isValidAddress(address)) {
            return
        }

        val normalizedPhone = Validator.normalizePhone(phone)

        pendingAddress = address
        pendingPhone = normalizedPhone
        isRegisterFlow = false

        viewModelScope.launch {
            val exists = repository.checkUserByAddressAndPhone(address, normalizedPhone)

            if (!exists) {
                onUserNotFound()
                return@launch
            }

            repository.sendVerificationCode(normalizedPhone, activity)
        }
    }

    fun submitCode(code: String) {
        repository.verifyCode(code)
    }

    fun onVerificationSuccess() {
        viewModelScope.launch {

            if (isRegisterFlow) {
                repository.saveUserToFirestore(
                    pendingFullName,
                    pendingAddress,
                    pendingPhone
                )
            }

            repository.updateFcmToken()
        }
    }

    fun signOut() {
        repository.signOut()
        pendingFullName = ""
        pendingAddress = ""
        pendingPhone = ""
        isRegisterFlow = false

        repository.resetState()
    }

    fun resetState() {
        repository.resetState()
    }
}
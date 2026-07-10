package com.example.meterly.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meterly.model.Address
import com.example.meterly.model.User
import com.example.meterly.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val repository = ProfileRepository()

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _addresses = MutableStateFlow<List<Address>>(emptyList())
    val addresses: StateFlow<List<Address>> = _addresses

    private val _currentAddress = MutableStateFlow<Address?>(null)
    val currentAddress: StateFlow<Address?> = _currentAddress

    init {
        repository.observeProfile {
            _user.value = it.user
            _addresses.value = it.addresses
            _currentAddress.value = it.currentAddress
        }
    }

    fun addAddress(address: String) {
        viewModelScope.launch {
            repository.addAddress(address)
        }
    }

    fun deleteAddress(id: String) {
        viewModelScope.launch {
            repository.deleteAddress(id)
        }
    }

    fun updateAddress(
        addressId: String,
        newAddress: String
    ) {
        viewModelScope.launch {
            repository.updateAddress(
                addressId,
                newAddress
            )
        }
    }

    fun setCurrentAddress(addressId: String) {
        viewModelScope.launch {
            repository.setCurrentAddress(addressId)
        }
    }

    override fun onCleared() {
        repository.removeListener()
        super.onCleared()
    }

    fun updateFullName(fullName: String) {
        viewModelScope.launch {
            repository.updateFullName(fullName)
        }
    }

    fun deleteAccount(
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            repository.deleteAccount()
            onSuccess()
        }
    }
}
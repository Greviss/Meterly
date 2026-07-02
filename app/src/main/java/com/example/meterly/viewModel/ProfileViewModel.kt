package com.example.meterly.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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
        loadProfile()
    }

    fun loadProfile() {
        viewModelScope.launch {

            _user.value = repository.getCurrentUser()

            _addresses.value =
                repository.getAddresses()

            _currentAddress.value =
                repository.getCurrentAddress()
        }
    }
}
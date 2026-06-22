package com.example.meterly.state

sealed class AuthState{
    object Idle: AuthState()
    object Loading : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    object Success: AuthState()
    data class Error(val message: String) : AuthState()
}
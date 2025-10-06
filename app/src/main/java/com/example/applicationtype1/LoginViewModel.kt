package com.example.applicationtype1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordVisible = MutableStateFlow(false)
    val passwordVisible: StateFlow<Boolean> = _passwordVisible

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun updateUsername(value: String) {
        _username.value = value
    }

    fun updatePassword(value: String) {
        _password.value = value
    }

    fun togglePasswordVisibility() {
        _passwordVisible.value = !_passwordVisible.value
    }

    fun clearErrorMessage() {
        _errorMessage.value = ""
    }

    private fun isValidLogin(login: String): Boolean {
        return login.isNotEmpty() && login.any { it.isLetter() } && login.all { it.isLetterOrDigit() }
    }

    private fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun validateAndLogin(onSuccess: () -> Unit) {
        val loginValid = isValidLogin(_username.value)
        val passwordValid = isValidPassword(_password.value)

        if (loginValid && passwordValid) {
            _errorMessage.value = ""
            onSuccess()
        } else {
            _errorMessage.value = when {
                !loginValid && !passwordValid -> "Login must contain at least one letter and must not contain any special symbols. Password must contain at least 8 symbols."
                !loginValid -> "Login must contain at least one letter and must not contain any special symbols."
                !passwordValid -> "Password must contain at least 8 symbols."
                else -> "Just exist. There is no error."
            }
        }
    }
}

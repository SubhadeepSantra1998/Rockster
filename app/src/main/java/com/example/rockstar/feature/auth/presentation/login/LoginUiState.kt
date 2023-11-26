package com.example.rockstar.feature.auth.presentation.login

import android.content.IntentSender

data class LoginUiState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val phNo: String = "",
    val phNoError: Int? = null,
    val isLoggedIn: Boolean = false,
)

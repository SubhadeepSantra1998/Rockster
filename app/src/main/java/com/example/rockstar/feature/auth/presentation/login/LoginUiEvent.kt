package com.example.rockstar.feature.auth.presentation.login

sealed class LoginUiEvent {

    data class PhoneNoChanged(val phNo: String) : LoginUiEvent()
    data class OtpChanged(val otp: String) : LoginUiEvent()
    data object SendOTP : LoginUiEvent()

    data object VerifyOTP : LoginUiEvent()

}

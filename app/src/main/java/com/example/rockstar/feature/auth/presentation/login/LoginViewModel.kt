package com.example.rockstar.feature.auth.presentation.login

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.rockstar.feature.auth.domain.repository.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthRepo
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

//    fun onEvent(event: LoginUiEvent) {
//
//        when(event){
//            is LoginUiEvent.PhoneNoChanged -> {
//                uiState = uiState.copy(phNo = event.phNo)
//            }
//
//            is LoginUiEvent.SendOTP -> {
//                sendOTP(uiState.phNo)
//            }
//
//            is LoginUiEvent.OtpChanged -> {
//                uiState = uiState.copy(otp = event.otp)
//            }
//
//            is LoginUiEvent.VerifyOTP ->{
//                verifyOTP(uiState.otp)
//            }
//        }
//    }


    fun sendOTP(
        mobile:String,
        activity: Activity
    ) = repo.createUserWithPhoneNo(mobile, activity)

    fun verifyOTP(
        code:String
    ) = repo.signIn(code)

}
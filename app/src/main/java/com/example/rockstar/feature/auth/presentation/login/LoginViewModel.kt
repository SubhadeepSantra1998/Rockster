package com.example.rockstar.feature.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    //private val loginUseCases: LoginUseCases,
) : ViewModel() {

    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onEvent(event: LoginUiEvent) {

        when(event){
            is LoginUiEvent.PhoneNoChanged -> {
                uiState = uiState.copy(phNo = event.phNo)
            }

            is LoginUiEvent.SendOTP -> {
                sendOTP()
            }
        }
    }

    private fun sendOTP() {
        TODO("Not yet implemented")
    }

}
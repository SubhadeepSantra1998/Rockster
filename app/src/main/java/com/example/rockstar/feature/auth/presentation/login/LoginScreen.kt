package com.example.rockstar.feature.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rockstar.R
import com.example.rockstar.common.component.BodyMediumTextComponent
import com.example.rockstar.common.component.HeadlineTextComponent
import com.example.rockstar.common.component.MediumTextButtonComponent
import com.example.rockstar.common.component.PhoneTextField
import com.example.rockstar.common.component.PrimaryButtonComponent

@Composable
fun LoginScreen(
    onOTPScreen: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {

    LoginScreenContent(
        onOTPScreen = onOTPScreen,
        uiState = viewModel.uiState,
        onEvent = {
            viewModel.onEvent(it)
        },
    )
}

@Composable
fun LoginScreenContent(
    onOTPScreen: () -> Unit,
    uiState: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit,
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Image(
                    painter = painterResource(id = R.drawable.login_image),
                    contentDescription = null,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                HeadlineTextComponent(text = stringResource(R.string.login))

                Spacer(modifier = Modifier.height(24.dp))

                PhoneTextField(textState = uiState.phNo, onTextChange = {
                    onEvent(LoginUiEvent.PhoneNoChanged(it))
                }, modifier = Modifier.fillMaxWidth())

                PrimaryButtonComponent(text = stringResource(id = R.string.send_otp), onClick = {

                })
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                BodyMediumTextComponent(text = stringResource(R.string.don_t_have_an_account))

                MediumTextButtonComponent(text = stringResource(R.string.register)) {

                }
            }
        }
    }
}

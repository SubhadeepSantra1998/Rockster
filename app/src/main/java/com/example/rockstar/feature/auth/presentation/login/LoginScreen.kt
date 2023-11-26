package com.example.rockstar.feature.auth.presentation.login

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rockstar.R
import com.example.rockstar.common.component.BodyMediumTextComponent
import com.example.rockstar.common.component.HeadlineTextComponent
import com.example.rockstar.common.component.LoadingBarComponent
import com.example.rockstar.common.component.MediumTextButtonComponent
import com.example.rockstar.common.component.OtpInputField
import com.example.rockstar.common.component.PhoneTextField
import com.example.rockstar.common.component.PrimaryButtonComponent
import com.example.rockstar.common.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    onHomeScreen:() -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
    activity: Activity,
) {

    val scope = rememberCoroutineScope()
    var mobile by remember { mutableStateOf("")}
    var otp by remember { mutableStateOf("")}
    var isDialog by remember{ mutableStateOf(false)}



    Box(
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

                PhoneTextField(textState = mobile, onTextChange = {
                    mobile = it
                }, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(24.dp))

                PrimaryButtonComponent(text = stringResource(id = R.string.send_otp), onClick = {
                    scope.launch(Dispatchers.Main){

                        viewModel.sendOTP(mobile, activity).collect{
                            when(it){
                                is Resource.Success->{
                                    isDialog = false
                                }
                                is Resource.Failure->{
                                    isDialog = false
                                }
                                Resource.Loading->{
                                    isDialog = true
                                }
                            }
                        }
                    }

                })

                Spacer(modifier = Modifier.height(80.dp))

                OtpInputField(onOtpChanged = { code ->
                    otp = code
                })

                Spacer(modifier = Modifier.height(24.dp))

                PrimaryButtonComponent(text = stringResource(id = R.string.verify_otp), onClick = {
                    scope.launch(Dispatchers.Main){
                        viewModel.verifyOTP(otp).collect{
                            when(it){
                                is Resource.Success->{
                                    isDialog = false
                                    onHomeScreen()
                                }
                                is Resource.Failure->{
                                    isDialog = false
                                }
                                Resource.Loading->{
                                    isDialog = true
                                }
                            }
                        }
                    }


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
        if(isDialog)
            LoadingBarComponent(modifier = Modifier.align(Alignment.Center))
    }
}

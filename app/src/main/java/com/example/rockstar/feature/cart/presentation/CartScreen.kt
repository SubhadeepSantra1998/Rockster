package com.example.rockstar.feature.cart.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rockstar.common.ConfirmationDialog
import com.example.rockstar.common.component.BackButtonComponent
import com.example.rockstar.common.component.HeadingTextComponent
import com.example.rockstar.common.component.IconButtonComponent
import com.example.rockstar.common.component.TitleMediumTextComponent
import com.example.rockstar.feature.cart.presentation.component.CartItemComponent
import com.example.rockstar.feature.home.data.model.Item
import com.example.rockstar.feature.viewModel.SharedViewModel

@Composable
fun CartScreen(
    modifier: Modifier,
    sharedViewModel: SharedViewModel,
    onBackScreen:() -> Unit,
    onHomeScreen:() -> Unit,
) {

    val cartItems by rememberUpdatedState(newValue = sharedViewModel._cartItems)

    var openDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = sharedViewModel._cartItems){
        Log.d("jbjbb",sharedViewModel._cartItems.toString())
    }


    Column(modifier = Modifier.fillMaxSize()) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                BackButtonComponent(
                    onClick = {
                        onBackScreen()
                    }
                )

                Spacer(modifier = Modifier.width(4.dp))
                HeadingTextComponent(text = "My Cart")
            }


            IconButton(
                onClick = {

                    openDialog = true
                }
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null)
            }

        }

        if (cartItems.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 4.dp)
                    .then(modifier),

                ){
                items(sharedViewModel._cartItems){ data->
                    CartItemComponent(data, sharedViewModel)
                }
            }
        }else{
            Box(modifier = Modifier.fillMaxSize()) {
                TitleMediumTextComponent(
                    text = "Empty Cart",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        if(openDialog){
            ConfirmationDialog(
                title = "Confirm",
                message = "Your order has been placed successfully",
                onConfirmation = {
                     sharedViewModel.resetData()
                    openDialog = false
                    onHomeScreen()
                },
                onDismissRequest = {
                    openDialog = false
                }
            )
        }
    }
}





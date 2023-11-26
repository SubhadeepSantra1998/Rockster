package com.example.rockstar.feature.cart.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rockstar.common.component.LabelTextComponent
import com.example.rockstar.common.component.TitleMediumTextComponent
import com.example.rockstar.feature.home.data.model.Item
import com.example.rockstar.feature.viewModel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemComponent(
    data: Item,
    sharedViewModel: SharedViewModel
) {

    ElevatedCard(
        onClick = {  },
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TitleMediumTextComponent(text = data.name)
                TitleMediumTextComponent(text = "Rs ${data.price}")

            }
            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                ElevatedButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { sharedViewModel.removeFromCart(data) },
                ) {
                    LabelTextComponent(text = "Remove")
                }
            }

        }
    }
}
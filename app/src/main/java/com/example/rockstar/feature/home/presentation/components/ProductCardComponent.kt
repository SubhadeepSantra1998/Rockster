package com.example.rockstar.feature.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rockstar.common.component.IconButtonComponent
import com.example.rockstar.common.component.TitleTextComponent
import com.example.rockstar.feature.home.data.model.Item
import com.example.rockstar.feature.viewModel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCardComponent(
    data: Item,
    sharedViewModel: SharedViewModel
) {
    ElevatedCard(
        onClick = { },
        modifier = Modifier.padding(8.dp)

    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(64.dp),
                model = data.imageUrl,
                contentScale = ContentScale.FillBounds,
                contentDescription = data.name
            )
            Spacer(modifier = Modifier.height(4.dp))
            TitleTextComponent(text = data.name)

            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TitleTextComponent(text = "Rs ${data.price}")
                IconButtonComponent(
                    icon = Icons.Default.Add,
                    onClick = {
                        sharedViewModel.addToCart(data)
                    }
                )
            }
        }
    }
}
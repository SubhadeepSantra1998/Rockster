package com.example.rockstar.feature.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.rockstar.feature.home.data.model.Item


class SharedViewModel: ViewModel() {

    val _cartItems = mutableListOf<Item>()




    fun addToCart(product: Item) {
        _cartItems.add(product)
    }

    fun removeFromCart(product: Item) {
        _cartItems.remove(product)
    }

    fun resetData(){
        _cartItems.clear()
    }
}
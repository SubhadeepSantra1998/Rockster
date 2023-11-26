package com.example.rockstar.feature.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rockstar.common.util.Route

sealed class BottomMenu(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val icon_focused: ImageVector
) {
    object Home : BottomMenu(
        route = Route.HOME_BOTTOM_BAR.value,
        title = "Home",
        icon = Icons.Outlined.Home,
        icon_focused = Icons.Filled.Home,
    )

    object Search : BottomMenu(
        route = Route.SEARCH_BOTTOM_BAR.value,
        title = "Search",
        icon = Icons.Outlined.Search,
        icon_focused = Icons.Filled.Search,
    )

    object Cart : BottomMenu(
        route = Route.CART_BOTTOM_BAR.value,
        title = "Cart",
        icon = Icons.Outlined.Favorite,
        icon_focused = Icons.Filled.Favorite,
    )

    object Profile : BottomMenu(
        route = Route.PROFILE_BOTTOM_BAR.value,
        title = "Profile",
        icon = Icons.Outlined.Person,
        icon_focused = Icons.Filled.Person,
    )
}
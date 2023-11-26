package com.example.rockstar.feature.home.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Laptop
import androidx.compose.material.icons.filled.MobileFriendly
import androidx.compose.material.icons.outlined.Laptop
import androidx.compose.material.icons.outlined.MobileFriendly
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItems(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

val tabItems = listOf(
    TabItems(
        "Mobile",
        Icons.Outlined.MobileFriendly,
        Icons.Filled.MobileFriendly,
    ),
    TabItems(
        "Laptop",
        Icons.Outlined.Laptop,
        Icons.Filled.Laptop,
    )
)

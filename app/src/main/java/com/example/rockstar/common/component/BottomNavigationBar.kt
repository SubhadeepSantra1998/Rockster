package com.example.rockstar.common.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rockstar.feature.main.BottomMenu

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    val bottomMenu = listOf(
        BottomMenu.Home,
        BottomMenu.Search,
        BottomMenu.Cart,
        BottomMenu.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavigationBarDestination = bottomMenu.any { it.route == currentDestination?.route }



    AnimatedVisibility(
        visible = bottomNavigationBarDestination,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        BottomNavigation(
            modifier = Modifier
                .graphicsLayer {
                    shape = RoundedCornerShape(
                        topStart = 28.dp,
                        topEnd = 28.dp
                    )
                    clip = true
                }
        ) {
            bottomMenu.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomMenu,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            androidx.compose.material.Icon(
                imageVector = screen.icon_focused,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}

@Composable
fun RowScope.NavigationBarItem(
    menu: BottomMenu,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val isSelected = currentDestination?.hierarchy?.any { it.route == menu.route } == true

    NavigationBarItem(
        label = {
            MenuLabelComponent(
                text = menu.title,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.SemiBold,
                fontColor = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
            )
        },
        icon = {
            Icon(
                imageVector = if (isSelected) menu.icon_focused else menu.icon,
                tint = if(isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                contentDescription = "Navigation Icon"
            )
        },
        selected = isSelected,
        onClick = {
            navController.navigate(menu.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
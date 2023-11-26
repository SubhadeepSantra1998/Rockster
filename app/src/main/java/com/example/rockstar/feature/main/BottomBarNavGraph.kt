package com.example.rockstar.feature.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rockstar.feature.cart.presentation.CartScreen
import com.example.rockstar.feature.home.presentation.HomeScreen
import com.example.rockstar.feature.viewModel.SharedViewModel
import com.example.rockstar.graph.Graph

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    modifier: Modifier,
    sharedViewModel: SharedViewModel
) {


    NavHost(
        navController = navController,
        startDestination = BottomMenu.Home.route,
        route = Graph.MAIN
    ) {

        composable(route = BottomMenu.Home.route) {
            HomeScreen(
                modifier = modifier,
                sharedViewModel = sharedViewModel
            )
        }
        composable(route = BottomMenu.Search.route) {
            // create and call search screen
        }
        composable(route = BottomMenu.Cart.route) {
            CartScreen(
                modifier = modifier,
                sharedViewModel = sharedViewModel,
                onBackScreen = {
                    navController.navigateUp()
                },
                onHomeScreen = {
                    navController.navigate(BottomMenu.Home.route)
                }
            )
        }
        composable(route = BottomMenu.Profile.route) {
        // create and call profile screen
        }

    }
}
package com.example.rockstar.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.rockstar.MainActivity
import com.example.rockstar.feature.auth.presentation.login.LoginScreen
import com.example.rockstar.graph.Graph

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    activity: MainActivity,
) {
    navigation(
        route = Graph.AUTH, startDestination = AuthRoute.Login.route,
    ) {

        composable(route = AuthRoute.Login.route) {
            LoginScreen(
                activity = activity,
                onHomeScreen = {
                    navController.navigate(Graph.MAIN)
                }
            )
        }

    }

}
package com.example.rockstar.graph

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rockstar.MainActivity
import com.example.rockstar.common.util.Route
import com.example.rockstar.feature.auth.navigation.authNavGraph
import com.example.rockstar.feature.main.MainScreen


@Composable
fun RootNavGraph(
    navController: NavHostController,
    activity: MainActivity,
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH
    ) {
        authNavGraph(navController = navController, activity = activity)

        // main screens
        composable(route = Graph.MAIN) {
            MainScreen()
        }
    }
}
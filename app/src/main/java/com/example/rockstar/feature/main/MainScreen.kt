package com.example.rockstar.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.rockstar.common.component.BottomNavigationBar
import com.example.rockstar.feature.viewModel.SharedViewModel

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    sharedViewModel: SharedViewModel = viewModel()
) {

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        BottomBarNavGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            sharedViewModel = sharedViewModel
        )
    }
}
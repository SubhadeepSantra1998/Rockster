package com.example.rockstar.feature.auth.navigation

import com.example.rockstar.common.util.Route


sealed class AuthRoute(val route: String) {
    data object Login : AuthRoute(route = Route.LOGIN.value)
    data object Signup : AuthRoute(route = Route.SIGNUP.value)
}

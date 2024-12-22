package com.ovinkin.pillsreminder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovinkin.pillsreminder.presentation.ui.MainScreen
import com.ovinkin.pillsreminder.presentation.ui.auth.LoginScreen
import com.ovinkin.pillsreminder.presentation.ui.auth.RegisterScreen
import com.ovinkin.pillsreminder.presentation.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationHost(navController: NavHostController) {

    val authViewModel = koinViewModel<AuthViewModel>()

    NavHost(
        navController, startDestination = NavigationItem.LoginScreen.route
    ) {
        composable(NavigationItem.MainScreen.route) {
            MainScreen(navController)
        }
        composable(NavigationItem.LoginScreen.route) {
            LoginScreen(authViewModel, navController)
        }
        composable(NavigationItem.RegisterScreen.route) {
            RegisterScreen(authViewModel, navController)
        }
    }
}
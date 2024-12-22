@file:OptIn(ExperimentalMaterial3Api::class)

package com.ovinkin.pillsreminder.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ovinkin.pillsreminder.presentation.ui.MainScreen
import com.ovinkin.pillsreminder.presentation.ui.auth.LoginScreen
import com.ovinkin.pillsreminder.presentation.ui.auth.RegisterScreen
import com.ovinkin.pillsreminder.presentation.ui.user.AddPatientScreen
import com.ovinkin.pillsreminder.presentation.ui.user.EditProfileScreen
import com.ovinkin.pillsreminder.presentation.ui.user.ProfileScreen
import com.ovinkin.pillsreminder.presentation.viewmodel.AuthViewModel
import com.ovinkin.pillsreminder.presentation.viewmodel.UserViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationHost(navController: NavHostController) {

    val authViewModel = koinViewModel<AuthViewModel>()
    val userViewModel = koinViewModel<UserViewModel>()

    NavHost(navController, startDestination = NavigationItem.LoginScreen.route) {
        composable(NavigationItem.MainScreen.route) {
            MainScreen(navController)
        }
        composable(NavigationItem.LoginScreen.route) {
            LoginScreen(authViewModel, navController)
        }
        composable(NavigationItem.RegisterScreen.route) {
            RegisterScreen(authViewModel, navController)
        }
        composable(NavigationItem.UserProfile.route) {
            ProfileScreen(navController, userViewModel)
        }
        composable(NavigationItem.EditProfile.route) {
            EditProfileScreen(navController, userViewModel)
        }
        composable(NavigationItem.AddPatient.route) {
            AddPatientScreen(navController, userViewModel)
        }
    }
}
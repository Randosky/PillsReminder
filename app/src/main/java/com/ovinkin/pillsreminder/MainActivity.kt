package com.ovinkin.pillsreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ovinkin.pillsreminder.presentation.navigation.BottomBar
import com.ovinkin.pillsreminder.presentation.navigation.NavigationHost
import com.ovinkin.pillsreminder.presentation.navigation.NavigationItem
import com.ovinkin.pillsreminder.ui.theme.PillsReminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PillsReminderTheme {
                ApplicationScreen()
            }
        }
    }

    @Preview
    @Composable
    fun ApplicationScreen() {
        val navController = rememberNavController()
        AppNavigation(navController)
    }

    @Composable
    fun AppNavigation(navigationController: NavHostController) {

        Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
            val navBackStackEntry by navigationController.currentBackStackEntryAsState()
            val currentDestinationRoute = navBackStackEntry?.destination?.route

            if (currentDestinationRoute == NavigationItem.LoginScreen.route || currentDestinationRoute == NavigationItem.RegisterScreen.route) {
                return@Scaffold
            }

            BottomBar(
                navController = navigationController,
            )
        }) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                NavigationHost(
                    navController = navigationController
                )
            }
        }
    }
}
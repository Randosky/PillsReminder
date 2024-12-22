package com.ovinkin.pillsreminder.presentation.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {


    val screens = listOf(
        NavigationItem.MainScreen,
        NavigationItem.UserProfile,
    )

    BottomNavigation(modifier = Modifier.height(72.dp)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestinationRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            BottomNavigationItem(selected = currentDestinationRoute == screen.route, label = {
                Text(text = screen.title)
            }, icon = { Icon(imageVector = screen.icon!!, contentDescription = "") }, onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
    }
}
package com.ovinkin.pillsreminder.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String, val title: String, val icon: ImageVector? = null
) {
    data object MainScreen : NavigationItem(
        route = "main", title = "Главная", icon = Icons.Rounded.Menu
    )

    data object LoginScreen : NavigationItem(
        route = "login", title = "Авторизация"
    )

    data object RegisterScreen : NavigationItem(
        route = "register", title = "Регистрация"
    )
}
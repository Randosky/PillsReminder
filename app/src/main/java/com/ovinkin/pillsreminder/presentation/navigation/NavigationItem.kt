package com.ovinkin.pillsreminder.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Person
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

    data object UserProfile : NavigationItem(
        route = "profile", title = "Профиль", icon = Icons.Rounded.Person
    )

    data object EditProfile : NavigationItem(
        route = "editProfile", title = "Редактировать Профиль"
    )

    data object AddPatient : NavigationItem(
        route = "addPatient", title = "Добавить пациента"
    )

    data object SetTreatment : NavigationItem(
        route = "setTreatment", title = "Назначить лечение"
    )

    data object TreatmentDetail : NavigationItem(
        route = "treatmentDetail", title = "Препарат"
    )
}
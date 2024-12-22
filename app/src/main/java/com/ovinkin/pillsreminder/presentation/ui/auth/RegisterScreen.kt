package com.ovinkin.pillsreminder.presentation.ui.auth

import android.os.Build
import android.service.autofill.UserData
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.ovinkin.pillsreminder.presentation.navigation.NavigationItem
import com.ovinkin.pillsreminder.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun RegisterScreen(authViewModel: AuthViewModel, navController: NavHostController) {
    val fullName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val selectedRole = remember { mutableStateOf("Выберите роль") }
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val roles = listOf("Врач", "Пациент")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Регистрация",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        TextField(
            value = fullName.value,
            onValueChange = { fullName.value = it },
            label = { Text("ФИО") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("Почта") },
            placeholder = { Text("example@example.com") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = selectedRole.value,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { isDropDownExpanded.value = true },
                    style = MaterialTheme.typography.bodyLarge
                )
                DropdownMenu(expanded = isDropDownExpanded.value, onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                    roles.forEach { role ->
                        DropdownMenuItem(text = { Text(text = role) }, onClick = {
                            selectedRole.value = role
                            isDropDownExpanded.value = false
                        })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.viewModelScope.launch {
                    val registered = authViewModel.register(
                        com.ovinkin.pillsreminder.data.model.UserData(
                            fullName.value, email.value, password.value, selectedRole.value
                        )
                    )
                    if (registered) {
                        navController.navigate(NavigationItem.MainScreen.route)
                    }
                }
            }, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрироваться")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate(NavigationItem.LoginScreen.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Уже есть аккаунт? Войти")
        }
    }
}
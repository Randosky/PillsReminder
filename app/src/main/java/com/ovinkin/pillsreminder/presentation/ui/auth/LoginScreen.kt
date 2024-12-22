package com.ovinkin.pillsreminder.presentation.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.ovinkin.pillsreminder.presentation.navigation.NavigationItem
import com.ovinkin.pillsreminder.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(authViewModel: AuthViewModel, navController: NavHostController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loading = authViewModel.loading
    val error = authViewModel.error

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Авторизация",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

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

        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Button(
            onClick = {
                authViewModel.viewModelScope.launch {
                    val authenticated = authViewModel.login(
                        com.ovinkin.pillsreminder.data.model.UserData(
                            "", email.value, password.value, ""
                        )
                    )
                    if (authenticated) {
                        navController.navigate(NavigationItem.MainScreen.route)
                    }
                }
            }, modifier = Modifier.fillMaxWidth(), enabled = !loading
        ) {
            Text(if (loading) "Загрузка..." else "Войти")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate(NavigationItem.RegisterScreen.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Нет аккаунта? Зарегистрируйтесь")
        }
    }
}
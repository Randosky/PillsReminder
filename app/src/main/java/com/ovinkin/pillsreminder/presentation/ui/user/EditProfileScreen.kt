package com.ovinkin.pillsreminder.presentation.ui.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.ovinkin.pillsreminder.data.model.UserData
import com.ovinkin.pillsreminder.data.model.UserRole
import com.ovinkin.pillsreminder.presentation.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var userData by remember { mutableStateOf<UserData?>(null) }
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        userData = userViewModel.getUserData()
        userData?.let {
            fullName = it.fullName ?: ""
            phone = it.phone ?: ""
            position = if (it.selectedRole == UserRole.DOCTOR.toString()) it.position ?: "" else ""
        }
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Редактировать Профиль") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
            }
        })
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(value = fullName,
                onValueChange = { fullName = it },
                label = { Text("ФИО") })
            OutlinedTextField(value = phone,
                onValueChange = { phone = it },
                label = { Text("Телефон") })
            if (userData?.selectedRole == UserRole.DOCTOR.toString()) {
                OutlinedTextField(value = position,
                    onValueChange = { position = it },
                    label = { Text("Должность") })
            }
            Button(onClick = {
                // Проверяем, что поля обязательные для врача заполнены
                if (fullName.isNotBlank() && phone.isNotBlank()) {
                    loading = true
                    val updatedUserData = userData?.copy(
                        fullName = fullName,
                        phone = phone,
                        position = if (userData?.selectedRole == UserRole.DOCTOR.toString()) position.takeIf { it.isNotBlank() } else null
                    )
                    // Запускаем обновление данных в ViewModel
                    userViewModel.viewModelScope.launch {
                        userViewModel.setUserData(updatedUserData)
                        loading = false
                        navController.popBackStack()
                    }
                } else {
                }
            }) {
                Text("Сохранить")
            }
        }
    }
}
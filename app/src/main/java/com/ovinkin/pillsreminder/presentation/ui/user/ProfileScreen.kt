package com.ovinkin.pillsreminder.presentation.ui.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ovinkin.pillsreminder.data.model.TreatmentData
import com.ovinkin.pillsreminder.data.model.UserData
import com.ovinkin.pillsreminder.data.model.UserRole
import com.ovinkin.pillsreminder.presentation.navigation.NavigationItem
import com.ovinkin.pillsreminder.presentation.viewmodel.UserViewModel

@Composable
fun ProfileScreen(navController: NavHostController, userViewModel: UserViewModel) {
    var userData by remember { mutableStateOf<UserData?>(null) }
    val loading = userViewModel.loading

    LaunchedEffect(Unit) {
        userData = userViewModel.getUserData()
    }

    if (loading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (userData != null) {
        LazyColumn(
            modifier = Modifier.padding(16.dp)
        ) {
            item {
                UserInfoCard(userData!!, navController)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (userData!!.selectedRole == UserRole.PATIENT.toString()) {
                item {
                    if (!userData!!.treatments.isNullOrEmpty()) {
                        userData!!.treatments?.let { TreatmentList(it) }
                    } else {
                        Text(
                            text = "Нет назначенного лечения",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            } else if (userData!!.selectedRole == UserRole.DOCTOR.toString()) {
                // Для врача показываем список пациентов и кнопку добавления
                item {
                    if (!userData!!.patients.isNullOrEmpty()) {
                        userData!!.patients?.let { PatientList(it) }
                    } else {
                        Text(text = "Нет пациентов", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    // Кнопка "Добавить пациента"
                    Button(onClick = {
                        navController.navigate(NavigationItem.AddPatient.route)
                    }) {
                        Text(text = "Добавить пациента")
                    }
                }
            }
        }
    } else {
        Text(text = "Данные пользователя недоступны", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun UserInfoCard(userData: UserData, navController: NavHostController) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = if (userData.selectedRole == UserRole.DOCTOR.toString()) "Профиль Врача" else "Профиль Пациента",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "ФИО: ${userData.fullName ?: "Не указано"}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Email: ${userData.email}", style = MaterialTheme.typography.bodyMedium)

            // Проверка на наличие номера телефона
            userData.phone?.let {
                Text(text = "Телефон: $it", style = MaterialTheme.typography.bodyMedium)
            } ?: run {
                Text(text = "Телефон: Не указан", style = MaterialTheme.typography.bodyMedium)
            }

            // Проверка на наличие должности для врача
            if (userData.selectedRole == UserRole.DOCTOR.toString()) {
                userData.position?.let {
                    Text(text = "Должность: $it", style = MaterialTheme.typography.bodyMedium)
                } ?: run {
                    Text(
                        text = "Должность: Не указана",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // Кнопка "Редактировать" для редактирования пользовательской информации
            Button(onClick = {
                navController.navigate(NavigationItem.AddPatient.route)
            }) {
                Text(text = "Редактировать")
            }
        }
    }
}

@Composable
fun PatientList(patients: List<UserData>) {
    Text(text = "Список пациентов", style = MaterialTheme.typography.titleMedium)
    LazyColumn {
        items(patients) { patient ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Пациент: ${patient.fullName ?: "Не указано"}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun TreatmentList(treatments: List<TreatmentData>) {
    Text(text = "Назначенное лечение", style = MaterialTheme.typography.titleMedium)
    LazyColumn {
        items(treatments) { treatment ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Название: ${treatment.name.takeIf { it.isNotBlank() } ?: "Не указано"}",
                        style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Статус: ${
                            treatment.status.takeIf {
                                it.toString().isNotEmpty()
                            } ?: "Не указан"
                        }",
                        style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = "Продолжительность: ${treatment.duration} дней",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}
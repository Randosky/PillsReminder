package com.ovinkin.pillsreminder.data.repository

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ovinkin.pillsreminder.data.model.UserData
import com.ovinkin.pillsreminder.domain.repository.IAuthRepository
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val context: Context
) : IAuthRepository {

    override suspend fun login(userData: UserData): Boolean {
        return try {
            auth.signInWithEmailAndPassword(userData.email, userData.password).await()
            true
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка аутентификации: ${e.message}", Toast.LENGTH_SHORT)
                .show()

            false
        }
    }

    override suspend fun register(userData: UserData): Boolean {
        return try {
            val result =
                auth.createUserWithEmailAndPassword(userData.email, userData.password).await()

            val userDataMap = mapOf(
                "fullName" to userData.fullName,
                "email" to userData.email,
                "role" to userData.selectedRole
            )

            firestore.collection("users").document(result.user?.uid ?: "").set(userDataMap).await()

            true
        } catch (e: Exception) {
            Toast.makeText(context, "Ошибка регистрации: ${e.message}", Toast.LENGTH_SHORT).show()

            false
        }
    }
}
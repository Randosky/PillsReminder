package com.ovinkin.pillsreminder.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ovinkin.pillsreminder.data.model.TreatmentData
import com.ovinkin.pillsreminder.data.model.TreatmentStatus
import com.ovinkin.pillsreminder.data.model.UserData
import com.ovinkin.pillsreminder.domain.repository.IUserRepository
import kotlinx.coroutines.tasks.await

class UserRepository(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) :
    IUserRepository {

    override suspend fun getUserData(): UserData? {
        val userId = auth.currentUser?.uid ?: return null

        return try {
            val documentSnapshot = firestore.collection("users").document(userId).get().await()
            val data = documentSnapshot.data

            UserData(fullName = data?.get("fullName") as? String,
                email = data?.get("email") as? String ?: "",
                password = "",
                selectedRole = data?.get("selectedRole") as? String,
                phone = data?.get("phone") as? String,
                position = data?.get("position") as? String,
                patients = (data?.get("patients") as? List<Map<String, Any>>)?.map {
                    UserData(
                        fullName = it["fullName"] as? String,
                        email = it["email"] as? String ?: "",
                        password = it["password"] as? String ?: "",
                        selectedRole = it["selectedRole"] as? String
                    )
                } ?: emptyList(),
                treatments = (data?.get("treatments") as? List<Map<String, Any>>)?.map {
                    TreatmentData(id = it["id"] as? String ?: "",
                        name = it["name"] as? String ?: "",
                        duration = it["duration"] as? Int ?: 0,
                        frequency = it["frequency"] as? Int ?: 0,
                        dosage = it["dosage"] as? Int ?: 0,
                        comment = it["comment"] as? String,
                        status = (it["status"] as? String)?.let { statusString ->
                            TreatmentStatus.valueOf(
                                statusString
                            )
                        } ?: TreatmentStatus.NOT_STARTED)
                } ?: emptyList())
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching user data", e)
            null
        }
    }

    override suspend fun setUserData(userData: UserData?): Unit? {
        val userId = auth.currentUser?.uid ?: return null

        return try {
            if (userData != null) {
                firestore.collection("users").document(userId).set(userData).await()
            }
            null
        } catch (e: Exception) {
            Log.e("UserRepository", "Error fetching user data", e)
            null
        }
    }
}
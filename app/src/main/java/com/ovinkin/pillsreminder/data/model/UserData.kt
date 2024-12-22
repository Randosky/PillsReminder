package com.ovinkin.pillsreminder.data.model

data class UserData(
    val fullName: String? = "",
    val email: String,
    val password: String,
    val selectedRole: String? = "",

    val phone: String? = null,
    val position: String? = null,
    val patients: List<UserData>? = emptyList(),

    val treatments: List<TreatmentData>? = emptyList()
)

enum class UserRole(private val roleName: String) {
    DOCTOR("Врач"),
    PATIENT("Пациент");

    override fun toString(): String {
        return roleName
    }
}
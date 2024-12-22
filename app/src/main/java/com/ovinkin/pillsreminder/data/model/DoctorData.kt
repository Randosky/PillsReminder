package com.ovinkin.pillsreminder.data.model

data class DoctorData(
    val id: String,
    val fullName: String,
    val email: String,
    val phone: String? = null,
    val position: String,
    val patients: List<PatientData> = emptyList()
)
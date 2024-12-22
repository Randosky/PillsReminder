package com.ovinkin.pillsreminder.domain.model

data class DoctorDomain(
    val id: String,
    val fullName: String,
    val email: String,
    val phone: String? = null,
    val position: String,
    val patients: List<PatientDomain> = emptyList()
)
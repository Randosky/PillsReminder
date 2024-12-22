package com.ovinkin.pillsreminder.data.model

data class PatientData(
    val id: String,
    val fullName: String,
    val email: String,
    val phone: String? = null,
    val treatments: List<TreatmentData> = emptyList()
)
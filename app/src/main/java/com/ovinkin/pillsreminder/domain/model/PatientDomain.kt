package com.ovinkin.pillsreminder.domain.model

data class PatientDomain(
    val id: String,
    val fullName: String,
    val email: String,
    val phone: String? = null,
    val treatments: List<TreatmentDomain> = emptyList()
)
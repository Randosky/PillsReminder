package com.ovinkin.pillsreminder.domain.model

import com.ovinkin.pillsreminder.data.model.TreatmentStatus

data class TreatmentDomain(
    val id: String,
    val name: String,
    val duration: Int,
    val frequency: Int,
    val dosage: Int,
    val comment: String? = null,
    val status: TreatmentStatus,
    val startDate: String? = null,
    val endDate: String? = null
)
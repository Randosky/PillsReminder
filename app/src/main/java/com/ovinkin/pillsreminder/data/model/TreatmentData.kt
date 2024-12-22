package com.ovinkin.pillsreminder.data.model

data class TreatmentData(
    val id: String,
    val name: String,
    val duration: Int,
    val frequency: Int,
    val dosage: Int,
    val comment: String? = null,
    val status: TreatmentStatus,
)

enum class TreatmentStatus {
    NOT_STARTED, IN_PROGRESS, COMPLETED
}
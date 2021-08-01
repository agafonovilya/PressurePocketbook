package ru.agafonovilya.pressurepocketbook.entities

data class Record(
    val time: Long,
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int
)
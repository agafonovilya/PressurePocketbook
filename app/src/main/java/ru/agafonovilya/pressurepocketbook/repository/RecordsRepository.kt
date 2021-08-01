package ru.agafonovilya.pressurepocketbook.repository

import kotlinx.coroutines.flow.Flow
import ru.agafonovilya.pressurepocketbook.entities.Record

interface RecordsRepository {
    fun records(): Flow<Set<Record>>
    fun saveRecord(record: Record)
}
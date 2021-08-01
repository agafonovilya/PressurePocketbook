package ru.agafonovilya.pressurepocketbook.ui.editor

import ru.agafonovilya.pressurepocketbook.entities.Record

interface SubmitRecordListener {
    fun submitRecord(record: Record)
}
package ru.agafonovilya.pressurepocketbook.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.agafonovilya.pressurepocketbook.repository.RecordsRepository

class MainViewModelFactory(
    private val recordsRepository: RecordsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when (modelClass) {
            MainViewModel::class.java -> MainViewModel(recordsRepository)
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T
}
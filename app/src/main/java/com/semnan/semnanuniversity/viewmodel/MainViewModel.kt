package com.semnan.semnanuniversity.viewmodel

import androidx.lifecycle.ViewModel
import com.semnan.semnanuniversity.data.MainRepositoryImpl
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.MainItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepositoryImpl): ViewModel() {

    val mainItems = MutableStateFlow<List<MainItems>>(emptyList())
    val facultyItems = MutableStateFlow<List<Faculty>>(emptyList())
    val faculty = MutableStateFlow<Faculty?>(null)

    fun getMainItems() {
        mainItems.value = mainRepository.getMainItems()
    }

    fun getFacultyItems(){
        facultyItems.value = mainRepository.getFacultyItems()
    }

}
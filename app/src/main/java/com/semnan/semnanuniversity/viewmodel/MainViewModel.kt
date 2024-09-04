package com.semnan.semnanuniversity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semnan.semnanuniversity.data.MainRepositoryImpl
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.MainItems
import com.semnan.semnanuniversity.data.model.Number
import com.semnan.semnanuniversity.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepositoryImpl): ViewModel() {

    val mainItems = MutableStateFlow<List<MainItems>>(emptyList())
    val facultyItems = MutableStateFlow<List<Faculty>>(emptyList())
    val numbers = MutableStateFlow<Resource<List<Number>>>(Resource.Loading)

    fun getMainItems() {
        mainItems.value = mainRepository.getMainItems()
    }

    fun getFacultyItems(){
        facultyItems.value = mainRepository.getFacultyItems()
    }

    fun getMoavenatItems() {
        facultyItems.value = mainRepository.getMoavenatItems()
    }

    fun getNumbers()  = viewModelScope.launch {
        numbers.value = Resource.Loading
        numbers.value = mainRepository.getNumbers()
    }

}
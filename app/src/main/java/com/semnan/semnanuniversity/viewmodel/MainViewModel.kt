package com.semnan.semnanuniversity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.semnan.semnanuniversity.data.MainRepositoryImpl
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.MainItems
import com.semnan.semnanuniversity.data.model.Number
import com.semnan.semnanuniversity.data.model.NumberFilters
import com.semnan.semnanuniversity.data.paging.NumberPagingSource
import com.semnan.semnanuniversity.network.ApiService
import com.semnan.semnanuniversity.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepositoryImpl, private val apiService: ApiService): ViewModel() {

    val mainItems = MutableStateFlow<List<MainItems>>(emptyList())
    val facultyItems = MutableStateFlow<List<Faculty>>(emptyList())
    val numbers = MutableStateFlow<Resource<List<Number>>>(Resource.Loading)

    val jobNameQuery = MutableStateFlow("")
    val subunitQuery = MutableStateFlow("")
    val numberQuery = MutableStateFlow("")
    val addressQuery = MutableStateFlow("")
    val isFilterShowing = MutableStateFlow(false)

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val numbersPager = combine(
        jobNameQuery.debounce(300),
        subunitQuery.debounce(300),
        numberQuery.debounce(300),
        addressQuery.debounce(300)
    ) { jobName, subunit, number, address ->
        NumberFilters(jobName, subunit, number, address)
    }.flatMapLatest { (jobName, subunit, number, address) ->
        Pager(
            PagingConfig(
                pageSize = NumberPagingSource.LIMIT,
                enablePlaceholders = false,
            )
        ) {
            NumberPagingSource(
                apiService = apiService,
                filters = NumberFilters(jobName, subunit, number, address)
            )
        }.flow.cachedIn(viewModelScope)
    }

    fun setJobNameQuery(query: String) {
        jobNameQuery.value = query
    }

    fun setSubunitQuery(query: String) {
        subunitQuery.value = query
    }

    fun setNumberQuery(query: String) {
        numberQuery.value = query
    }

    fun setAddressQuery(query: String) {
        addressQuery.value = query
    }

    fun toggleIsSearchShowing() {
        isFilterShowing.value = !isFilterShowing.value
    }

    fun getMainItems() {
        mainItems.value = mainRepository.getMainItems()
    }

    fun getFacultyItems(){
        facultyItems.value = mainRepository.getFacultyItems()
    }

    fun getMoavenatItems() {
        facultyItems.value = mainRepository.getMoavenatItems()
    }



}
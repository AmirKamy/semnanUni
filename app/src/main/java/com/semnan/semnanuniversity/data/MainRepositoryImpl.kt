package com.semnan.semnanuniversity.data

import com.semnan.semnanuniversity.data.datasources.MainLocalDataSource
import com.semnan.semnanuniversity.data.datasources.MainRemoteDataSource
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.Number
import com.semnan.semnanuniversity.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: MainLocalDataSource,
    private val remoteDataSource: MainRemoteDataSource
) : MainRepository {

    override fun getMainItems() = localDataSource.getMainItems()

    override fun getFacultyItems() = localDataSource.getFacultyItems()

    override fun getMoavenatItems() = localDataSource.getMoavenatItems()

    override suspend fun getNumbers(): Resource<List<Number>> = withContext(Dispatchers.IO) {
        safeApiCall { remoteDataSource.getNumbers() }
    }

}
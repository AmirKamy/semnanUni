package com.semnan.semnanuniversity.data

import com.semnan.semnanuniversity.data.datasources.MainLocalDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val localDataSource: MainLocalDataSource): MainRepository {

    override fun getMainItems() = localDataSource.getMainItems()

}
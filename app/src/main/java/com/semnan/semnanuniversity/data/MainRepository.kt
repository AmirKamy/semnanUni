package com.semnan.semnanuniversity.data

import com.semnan.semnanuniversity.base.BaseRepository
import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.MainItems
import com.semnan.semnanuniversity.data.model.Number
import com.semnan.semnanuniversity.network.Resource

interface MainRepository: BaseRepository {

    fun getMainItems(): List<MainItems>

    fun getFacultyItems(): List<Faculty>

    fun getMoavenatItems(): List<Faculty>


}
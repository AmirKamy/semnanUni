package com.semnan.semnanuniversity.data

import com.semnan.semnanuniversity.data.model.Faculty
import com.semnan.semnanuniversity.data.model.MainItems

interface MainRepository {

    fun getMainItems(): List<MainItems>

    fun getFacultyItems(): List<Faculty>

    fun getMoavenatItems(): List<Faculty>

}
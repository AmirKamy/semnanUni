package com.semnan.semnanuniversity.data

import com.semnan.semnanuniversity.data.model.MainItems

interface MainRepository {

    fun getMainItems(): List<MainItems>

}
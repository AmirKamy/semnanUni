package com.semnan.semnanuniversity.network

import android.content.Context
import com.semnan.semnanuniversity.data.model.Number
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {


    @GET("people")
    suspend fun getNumbers(): List<Number>



    companion object {

        private val BASE_URL: String = "https://118.semnan.ac.ir/api/"

        fun create(context: Context): ApiService {

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }



}


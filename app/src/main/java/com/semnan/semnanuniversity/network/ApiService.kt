package com.semnan.semnanuniversity.network

import android.content.Context
import com.semnan.semnanuniversity.data.model.Number
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("people")
    suspend fun getNumbers(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("job_name") job_name: String? = null,
        @Query("subunit") subunit: String? = null,
        @Query("number") number: String? = null,
        @Query("address") address: String? = null,
    ): List<Number>



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


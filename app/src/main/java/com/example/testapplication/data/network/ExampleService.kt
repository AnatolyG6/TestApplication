package com.example.testapplication.data.network

import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleService {
    @GET("get-some-data")
    fun getSomeData(
        @Query("parameter") parameter: String,
    ): Call<ExampleRestModel>
}

@JsonClass(generateAdapter = true)
data class ExampleRestModel(
    val attribute: String
)
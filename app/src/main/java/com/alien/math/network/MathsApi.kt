package com.alien.math.network

import com.alien.math.model.PostModel
import com.alien.math.model.ResponseModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


private const val BASE_URL = "http://api.mathjs.org/v4/"


interface MathsApi {

    companion object{
        operator fun invoke():MathsApi{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MathsApi::class.java)
        }
    }

    @Headers("Content-Type: application/json")
    @POST("./")
    fun postExpression(@Body postModel: PostModel):Call<ResponseModel>

}
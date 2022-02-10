package com.datapacker.surveyor.data


import com.datapacker.surveyor.model.LoginBody
import com.datapacker.surveyor.model.Surveyor
import com.datapacker.surveyor.model.Token
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Api {


    @Headers("Accept: application/json")
    @POST("/api/token/")
    suspend fun parseTokenApi(@Body loginBody: LoginBody): Response<Token>



    @POST("/api/surveyor/")
    suspend fun loadSurveyorApi(): Response<Surveyor>

}
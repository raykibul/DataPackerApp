package com.datapacker.surveyor.data


import com.datapacker.surveyor.data.model.LoginBody
import com.datapacker.surveyor.data.model.Survey
import com.datapacker.surveyor.data.model.Surveyor
import com.datapacker.surveyor.data.model.Token
import retrofit2.Response
import retrofit2.http.*

interface Api {


    @Headers("Accept: application/json")
    @POST("/api/token/")
    suspend fun ParseApiToken(@Body loginBody: LoginBody): Response<Token>



    @POST("/api/surveyor/")
    suspend fun LoadSurveyor(): Response<Surveyor>


    @POST("/api/survey/")
    suspend fun LoadSurvey(): Response<List<Survey>>
}
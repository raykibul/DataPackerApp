package com.datapacker.surveyor.data

import com.datapacker.surveyor.data.model.*

class Repository {

    suspend fun parseToken(loginBody: LoginBody):retrofit2.Response<Token> {
       return RetrofitInstance.API.ParseApiToken(loginBody);
    }
    suspend fun loadSurveyor():retrofit2.Response<Surveyor> {
        return RetrofitInstance.API.LoadSurveyor();
    }
    suspend fun LoadSurvey():retrofit2.Response<List<Survey>> {
        return RetrofitInstance.API.LoadSurvey();
    }
   /* suspend fun sendData(username:String,password:String,amount:String,sender:String,transaction:String,gateway:String): Call<ResponseBody> {
        return RetrofitInstance.ADMIN_API.sendRequest(username,password,amount,transaction,sender,Constants.PACKGENAME,gateway)
    }

    suspend fun loadPayements(username: String,password: String):retrofit2.Response<List<Payment>>{
        return RetrofitInstance.ADMIN_API.loadHistory(username,password)
    }
    suspend fun loadOperatorSms(username: String, password: String): List<OperatorSMS>{
        return RetrofitInstance.ADMIN_API.loadOperatorSms(username,password)
    }

    suspend fun loadOperatorList(username: String, password: String): List<Operator>{
        return RetrofitInstance.ADMIN_API.loadOperatorList(username,password)
    }
     suspend fun loadgateways(username: String, password: String):retrofit2.Response<List<Process>>{
        return RetrofitInstance.ADMIN_API.loadgateways(username,password)
    }
    suspend fun cancell(username:String,password:String,paymentId:String): Call<ResponseBody> {
        return RetrofitInstance.ADMIN_API.Cancell(username,password ,paymentId,Constants.PACKGENAME )
    }*/


}
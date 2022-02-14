package com.datapacker.surveyor.model

import com.datapacker.surveyor.data.Api
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    var client = OkHttpClient.Builder().addInterceptor { chain ->
        val token = Token.getInstance()
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer " + token?.access)
            .build()
        chain.proceed(newRequest)
    }.build()


    var gson = GsonBuilder()
        .setLenient()
        .create()



    private  val retrofit by lazy{

        Retrofit.Builder()
            .client(client)
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }



   val API: Api by lazy {
       retrofit.create(Api::class.java)
   }

}
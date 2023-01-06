package com.example.alarmapp

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceBuilder {
    private const val BASEURL = "https://brogrow.azurewebsites.net"
    private const val BASEURLSETU="https://hackathon.pirimidtech.com/hackathon/"
    var gson = GsonBuilder()
        .setLenient()
        .create()
    fun buildService(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiInterface::class.java)
    }

}

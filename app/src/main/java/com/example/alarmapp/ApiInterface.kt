package com.example.alarmapp

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("/loan")
    fun getDetails(): Call<ResponseBody>
}
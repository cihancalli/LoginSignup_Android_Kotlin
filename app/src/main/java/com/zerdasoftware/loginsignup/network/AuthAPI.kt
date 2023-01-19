package com.zerdasoftware.loginsignup.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("name") email:String,
        @Field("password") password:String,
    ):Any
}
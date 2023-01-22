package com.zerdasoftware.loginsignup.network

import com.zerdasoftware.loginsignup.responses.LoginResponse
import com.zerdasoftware.loginsignup.responses.MessageResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthAPI {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("name") username:String,
        @Field("password") password:String,
    ):LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String,
    ): MessageResponse
}
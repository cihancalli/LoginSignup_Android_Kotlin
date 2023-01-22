package com.zerdasoftware.loginsignup.repository

import com.zerdasoftware.loginsignup.network.AuthAPI

class AuthRepository(private val api:AuthAPI) : BaseRepository(api) {

    suspend fun login(username:String,password:String ) = safeApiCall{ api.login(username,password) }
    suspend fun register(name:String,email:String,password:String ) = safeApiCall{ api.register(name,email,password) }

}
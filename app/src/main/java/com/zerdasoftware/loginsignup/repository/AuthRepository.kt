package com.zerdasoftware.loginsignup.repository

import com.zerdasoftware.loginsignup.network.AuthAPI

class AuthRepository(private val api:AuthAPI) : BaseRepository(api) {

    suspend fun login(email:String,password:String ) = safeApiCall{ api.login(email,password) }

}
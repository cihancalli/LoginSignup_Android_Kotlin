package com.zerdasoftware.loginsignup.data.responses

import com.google.gson.annotations.SerializedName


//Login Olduktan sonra geriye dönen data
data class LoginResponse (
    @SerializedName("token")
    val token: String
    )
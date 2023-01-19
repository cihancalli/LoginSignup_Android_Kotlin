package com.zerdasoftware.loginsignup.responses

import com.google.gson.annotations.SerializedName


//Login Olduktan sonra geriye dönen data
class LoginResponse (
    @SerializedName("token")
    val token: String
    )
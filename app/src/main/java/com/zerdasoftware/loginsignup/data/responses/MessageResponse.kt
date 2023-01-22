package com.zerdasoftware.loginsignup.data.responses


import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("message")
    val message: String
)
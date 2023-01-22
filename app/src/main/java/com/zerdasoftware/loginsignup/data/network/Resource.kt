package com.zerdasoftware.loginsignup.data.network

import okhttp3.ResponseBody


//apiye istek yaparken geriye dönen hata veya dataları göstermek için
sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()
}
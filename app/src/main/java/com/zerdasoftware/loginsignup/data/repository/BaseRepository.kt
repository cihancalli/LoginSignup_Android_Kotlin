package com.zerdasoftware.loginsignup.data.repository

import com.zerdasoftware.loginsignup.data.network.AuthAPI
import com.zerdasoftware.loginsignup.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository(api: AuthAPI) {

    suspend fun <T> safeApiCall(
        apiCall: suspend ()-> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable:Throwable) {
                when(throwable) {
                    is HttpException-> {
                        Resource.Failure(false,throwable.code(),throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true,null,null)
                    }
                }
            }
        }
    }
}
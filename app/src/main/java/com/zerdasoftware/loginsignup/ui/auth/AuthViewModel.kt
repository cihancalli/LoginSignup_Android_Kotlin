package com.zerdasoftware.loginsignup.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerdasoftware.loginsignup.network.Resource
import com.zerdasoftware.loginsignup.repository.AuthRepository
import com.zerdasoftware.loginsignup.responses.LoginResponse
import com.zerdasoftware.loginsignup.responses.MessageResponse
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
        get() = _loginResponse

    fun login (username:String,password:String) =  viewModelScope.launch {
        _loginResponse.value = repository.login(username, password)
    }

    private val _registerResponse: MutableLiveData<Resource<MessageResponse>> = MutableLiveData()
    val registerResponse: LiveData<Resource<MessageResponse>>
        get() = _registerResponse

    fun register (name:String,email:String,password:String) =  viewModelScope.launch {
        _registerResponse.value = repository.register(name,email,password)
    }

}
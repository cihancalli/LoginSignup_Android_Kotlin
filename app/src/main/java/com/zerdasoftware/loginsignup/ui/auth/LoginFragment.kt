@file:Suppress("DEPRECATION")

package com.zerdasoftware.loginsignup.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zerdasoftware.loginsignup.databinding.FragmentLoginBinding
import com.zerdasoftware.loginsignup.network.AuthAPI
import com.zerdasoftware.loginsignup.repository.AuthRepository
import com.zerdasoftware.loginsignup.ui.base.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater:LayoutInflater,container:ViewGroup?)
        = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthAPI::class.java))


}
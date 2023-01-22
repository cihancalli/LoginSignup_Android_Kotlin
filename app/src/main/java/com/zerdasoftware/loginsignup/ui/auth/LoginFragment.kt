@file:Suppress("DEPRECATION")

package com.zerdasoftware.loginsignup.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zerdasoftware.loginsignup.databinding.FragmentLoginBinding
import com.zerdasoftware.loginsignup.network.AuthAPI
import com.zerdasoftware.loginsignup.network.Resource
import com.zerdasoftware.loginsignup.repository.AuthRepository
import com.zerdasoftware.loginsignup.ui.base.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding,AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.buttonLogin.setOnClickListener {
            val name = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            viewModel.login(name,password)
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) { data->
            when (data){
                is Resource.Success -> {
                    println("Resource.Success ${data.value.token}")
                }
                is Resource.Failure -> {
                    println("Resource.Failure ${data.isNetworkError}_${data.errorBody}_${data.errorCode}")
                }
            }
        }
        binding.textViewRegisterNow.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            androidx.navigation.Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater:LayoutInflater,container:ViewGroup?)
        = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthAPI::class.java))


}
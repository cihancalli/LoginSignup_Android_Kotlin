package com.zerdasoftware.loginsignup.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zerdasoftware.loginsignup.databinding.FragmentRegisterBinding
import com.zerdasoftware.loginsignup.data.network.AuthAPI
import com.zerdasoftware.loginsignup.data.network.Resource
import com.zerdasoftware.loginsignup.data.repository.AuthRepository
import com.zerdasoftware.loginsignup.ui.base.BaseFragment

class RegisterFragment : BaseFragment<AuthViewModel, FragmentRegisterBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.buttonRegister.setOnClickListener {
            val name = binding.editTextTextPersonName.text.toString().trim()
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            viewModel.register(name,email,password)
        }

        viewModel.registerResponse.observe(viewLifecycleOwner) { data->
            when (data){
                is Resource.Success -> {
                    println("Resource.Success ${data.value}")
                }
                is Resource.Failure -> {
                    println("Resource.Failure ${data.isNetworkError}_${data.errorBody}_${data.errorCode}")
                }
            }
        }

        binding.textViewLoginNow.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            androidx.navigation.Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(inflater:LayoutInflater,container:ViewGroup?)
            = FragmentRegisterBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthAPI::class.java),userPreferences)


}
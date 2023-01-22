@file:Suppress("DEPRECATION")

package com.zerdasoftware.loginsignup.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.zerdasoftware.loginsignup.databinding.FragmentLoginBinding
import com.zerdasoftware.loginsignup.data.network.AuthAPI
import com.zerdasoftware.loginsignup.data.network.Resource
import com.zerdasoftware.loginsignup.data.repository.AuthRepository
import com.zerdasoftware.loginsignup.enable
import com.zerdasoftware.loginsignup.startNewActivity
import com.zerdasoftware.loginsignup.ui.base.BaseFragment
import com.zerdasoftware.loginsignup.ui.home.HomeActivity
import com.zerdasoftware.loginsignup.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel,FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        binding.editTextTextPassword.addTextChangedListener {
            val name = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(name.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            val name = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()
            binding.progressbar.visible(true)
            viewModel.login(name,password)
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) { data->
            binding.progressbar.visible(false)
            when (data){
                is Resource.Success -> {
                    println("Resource.Success ${data.value.token}")

                        viewModel.saveAuthToken(data.value.token)
                        requireActivity().startNewActivity(HomeActivity::class.java)
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

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthAPI::class.java),userPreferences)


}
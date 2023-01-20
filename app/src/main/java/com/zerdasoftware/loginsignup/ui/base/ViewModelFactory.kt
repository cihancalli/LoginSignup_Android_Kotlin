@file:Suppress("UNCHECKED_CAST")

package com.zerdasoftware.loginsignup.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zerdasoftware.loginsignup.repository.AuthRepository
import com.zerdasoftware.loginsignup.repository.BaseRepository
import com.zerdasoftware.loginsignup.ui.auth.AuthViewModel

class ViewModelFactory(private val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            //modelClass.isAssignableFrom(NewViewModel::class.java) -> NewViewModel(repository as NewRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }
}
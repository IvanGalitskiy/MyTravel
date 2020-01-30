package com.devandfun.mytravel.login.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devandfun.mytravel.login.model.GetProfileUseCase

class LoginViewModelFactory : ViewModelProvider.Factory {
    var getProfileUseCase: GetProfileUseCase? = null

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> LoginViewModel(getProfileUseCase!!)
            else -> error("Unusported type")
        } as T
    }
}
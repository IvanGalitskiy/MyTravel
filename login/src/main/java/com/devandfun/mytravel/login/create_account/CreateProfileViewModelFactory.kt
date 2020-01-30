package com.devandfun.mytravel.login.create_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devandfun.mytravel.login.create_account.model.CreateAccountUseCase

class CreateProfileViewModelFactory : ViewModelProvider.Factory {
    var createAccountUseCase: CreateAccountUseCase? = null

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CreateProfileViewModel::class.java -> CreateProfileViewModel(
                createAccountUseCase!!
            )
            else -> error("Unsuported type ${modelClass.simpleName}")
        } as T
    }
}
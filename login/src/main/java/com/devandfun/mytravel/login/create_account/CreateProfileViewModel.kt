package com.devandfun.mytravel.login.create_account

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devandfun.mytravel.login.create_account.model.CreateAccountUseCase
import kotlinx.coroutines.launch

class CreateProfileViewModel(private val createAccountUseCase: CreateAccountUseCase) : ViewModel() {
    val name = MutableLiveData<String>()
    val accountCreation = MutableLiveData<Throwable?>()

    fun setProfileName(name: String) {
        this.name.postValue(name)
    }

    suspend fun createAccount() {
        try {
            viewModelScope.launch {
                createAccountUseCase.createAccount(name.value ?: "")
                accountCreation.postValue(null)
            }
        } catch (ex: Throwable){
            accountCreation.postValue(ex)
        }
    }
}
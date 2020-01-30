package com.devandfun.mytravel.login.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.login.model.GetProfileUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel(private val getProfileUseCase: GetProfileUseCase) : ViewModel() {
    val profiles = MutableLiveData<List<Profile>>()
    val state = MutableLiveData<LoginState>()

    init {
        state.postValue(LoginState.DEFAULT)
    }

    fun getProfiles() {
        viewModelScope.launch {
            try {
                getProfileUseCase.getAccounts().collect {
                    profiles.postValue(it)
                }
            } catch (ex: Exception) {
                profiles.postValue(emptyList())
            }
        }
    }

    fun addProfile(){
        state.postValue(LoginState.ADDING_PROFILE)
    }

    fun cancelProfileAdding(){
        state.postValue(LoginState.DEFAULT)
    }
}
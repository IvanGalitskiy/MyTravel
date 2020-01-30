package com.devandfun.mytravel.login.create_account.model

import com.devandfun.mytravel.login.data.ProfileRepository

class CreateAccountUseCaseImpl(private val profileRepository: ProfileRepository):
    CreateAccountUseCase {
    override suspend fun createAccount(accountName: String) {

    }
}
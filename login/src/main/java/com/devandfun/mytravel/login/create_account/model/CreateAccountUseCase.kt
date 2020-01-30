package com.devandfun.mytravel.login.create_account.model

interface CreateAccountUseCase {
    suspend fun createAccount(accountName: String)
}
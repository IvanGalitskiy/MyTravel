package com.devandfun.mytravel.login.model

import com.devandfun.mytravel.base.entities.Profile
import kotlinx.coroutines.flow.Flow

interface GetProfileUseCase {
    suspend fun getAccounts(): Flow<List<Profile>>
}
package com.devandfun.mytravel.login.data

import com.devandfun.mytravel.base.entities.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfiles(): Flow<List<Profile>>
    suspend fun createProfile(profile: Profile)
}
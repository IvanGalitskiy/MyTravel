package com.devandfun.mytravel.login.model

import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.login.data.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetProfileUseCaseImpl(private val profileRepository: ProfileRepository): GetProfileUseCase {
    override suspend fun getAccounts(): Flow<List<Profile>> {
        return profileRepository.getProfiles()
    }
}
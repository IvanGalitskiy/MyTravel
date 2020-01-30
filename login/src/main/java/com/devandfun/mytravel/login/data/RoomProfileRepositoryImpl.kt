package com.devandfun.mytravel.login.data

import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.login.data.database.ProfileDao
import kotlinx.coroutines.flow.Flow

class RoomProfileRepositoryImpl(private val profileDao: ProfileDao) : ProfileRepository {
    override suspend fun createProfile(profile: Profile) {
        return profileDao.createProfile(profile)
    }

    override suspend fun getProfiles(): Flow<List<Profile>> {
        return profileDao.getProfiles()
    }
}
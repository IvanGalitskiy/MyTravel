package com.devandfun.mytravel.login.di

import com.devandfun.mytravel.base.di.DependenciesProvider
import com.devandfun.mytravel.login.data.ProfileRepository
import com.devandfun.mytravel.login.data.RoomProfileRepositoryImpl
import com.devandfun.mytravel.login.data.database.ProfileDao

class ProfileRepositoryProvider : DependenciesProvider<ProfileRepository>() {

    private fun instantiateNewInstanceOfProfileRepository(profileDao: ProfileDao): ProfileRepository {
        instance = RoomProfileRepositoryImpl(profileDao)
        return instance!!
    }

    fun provideProfileRepository(profileDao: ProfileDao?): ProfileRepository {
        return getExistingInstance()
            ?: profileDao?.let { instantiateNewInstanceOfProfileRepository(it) }
            ?: error("Can not instantiate profile repository, null as param given")
    }

}
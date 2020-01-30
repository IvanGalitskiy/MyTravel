package com.devandfun.mytravel.login.ui

import com.devandfun.mytravel.base.di.Destroyable
import com.devandfun.mytravel.base.entities.Profile
import com.devandfun.mytravel.login.create_account.CreateProfileFeature
import com.devandfun.mytravel.login.data.ProfileRepository
import com.devandfun.mytravel.login.data.database.ProfileDao
import com.devandfun.mytravel.login.di.ProfileRepositoryProvider

class LoginDependencies : Destroyable, CreateProfileFeature.Dependencies {
    private var profileRepositoryProvider = ProfileRepositoryProvider()

    override fun callback(): CreateProfileFeature.Callback {
        return object : CreateProfileFeature.Callback {
            override fun onProfileWasCreated(selectedProfile: Profile) {

            }
        }
    }

    override fun profileRepository(): ProfileRepository {
        return provideProfileRepository(null)
    }


    fun provideProfileRepository(profileDao: ProfileDao? = null): ProfileRepository {
        return profileRepositoryProvider.provideProfileRepository(profileDao)
    }

    override fun destroy() {
        profileRepositoryProvider.destroy()
    }

}
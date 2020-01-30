package com.devandfun.mytravel.login.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devandfun.mytravel.base.entities.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM traveler_profile")
    fun getProfiles(): Flow<List<Profile>>
    @Insert
    suspend fun createProfile(profile: Profile)
}
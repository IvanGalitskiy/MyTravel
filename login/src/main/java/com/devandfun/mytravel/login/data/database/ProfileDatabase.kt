package com.devandfun.mytravel.login.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devandfun.mytravel.base.data.utils.DateConverter
import com.devandfun.mytravel.base.entities.Profile

@Database(entities = [Profile::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class ProfileDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}
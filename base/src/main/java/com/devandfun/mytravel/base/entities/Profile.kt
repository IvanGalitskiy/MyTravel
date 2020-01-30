package com.devandfun.mytravel.base.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "traveler_profile")
data class Profile(
    @PrimaryKey
    @ColumnInfo(name = "profile_id")
    val id: String,
    @ColumnInfo(name = "profile_name")
    val name: String,
    var dateOfLastVisit: Date?
)
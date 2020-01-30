package com.devandfun.mytravel.base.data.utils

import androidx.room.TypeConverter
import java.util.*


class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long {
        return date?.time ?: 0L
      }
}
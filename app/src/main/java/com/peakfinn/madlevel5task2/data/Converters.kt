package com.peakfinn.madlevel5task2.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Finn Bon on 12/12/2020.
*/
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}

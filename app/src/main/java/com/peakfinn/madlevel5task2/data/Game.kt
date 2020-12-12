package com.peakfinn.madlevel5task2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Finn Bon on 12/12/2020.
 */
@Entity(tableName = "gameTable")
class Game(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "platform")
    val platform: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: Date,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long? = null
)
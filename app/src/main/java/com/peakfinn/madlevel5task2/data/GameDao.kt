package com.peakfinn.madlevel5task2.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Finn Bon on 03/12/2020.
 */
@Dao
interface GameDao {

    @Query("SELECT * FROM gameTable")
    fun getGames(): LiveData<List<Game>>

    @Insert
    suspend fun insert(note: Game)

    @Update
    suspend fun update(note: Game)

    @Delete
    suspend fun remove(note: Game)

}
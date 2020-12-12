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
    suspend fun insert(game: Game)

    @Update
    suspend fun update(game: Game)

    @Delete
    suspend fun remove(game: Game)

    @Query("DELETE FROM gameTable")
    suspend fun removeAll()

}
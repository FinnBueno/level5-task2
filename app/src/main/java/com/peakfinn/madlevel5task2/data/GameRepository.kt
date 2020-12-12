package com.peakfinn.madlevel5task2.data

import android.content.Context
import androidx.lifecycle.LiveData

/**
 * Created by Finn Bon on 12/12/2020.
 */
class GameRepository(context: Context) {

    private val dao: GameDao

    init {
        val database = GameDatabase.getDatabase(context)
        dao = database!!.gameDao()
    }

    fun getGames(): LiveData<List<Game>> {
        return dao.getGames()
    }

    suspend fun insert(game: Game) {
        dao.insert(game)
    }

}

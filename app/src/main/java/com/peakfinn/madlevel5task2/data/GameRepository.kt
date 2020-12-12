package com.peakfinn.madlevel5task2.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Created by Finn Bon on 12/12/2020.
 */
class GameRepository(context: Context) {

    private val dao: GameDao

    init {
        val database = GameDatabase.getDatabase(context)
        dao = database!!.gameDao()
    }

    fun getGames(): LiveData<List<Game>> = dao.getGames()

    suspend fun insert(game: Game) {
        dao.insert(game)
    }

    suspend fun remove(game: Game) {
        dao.remove(game)
    }

    suspend fun removeAll() {
        dao.removeAll()
    }

}

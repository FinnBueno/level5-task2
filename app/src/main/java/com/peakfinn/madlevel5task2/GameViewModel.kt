package com.peakfinn.madlevel5task2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.peakfinn.madlevel5task2.data.Game
import com.peakfinn.madlevel5task2.data.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * Created by Finn Bon on 12/12/2020.
 */
class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertGame(title: String, platform: String, releaseDate: Date) {
        val game = Game(
            title = title,
            platform = platform,
            releaseDate = releaseDate
        )

        if (gameIsValid(game)) {
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    repo.insert(game)
                    success.value = true
                }
            }
        }
    }

    private fun gameIsValid(game: Game): Boolean {
        return when {
            game.title.isBlank() -> {
                error.value = "Title is required"
                false
            }
            else -> true
        }
    }
}
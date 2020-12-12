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
import java.time.YearMonth
import java.util.*

/**
 * Created by Finn Bon on 12/12/2020.
 */
class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val games = repo.getGames()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    fun insertGame(title: String, platform: String, day: Int, month: Int, year: Int) {
        if (gameIsValid(title, platform, day, month, year)) {
            val releaseDate = Date(Date.UTC(year, month, day, 0, 0, 0))
            val game = Game(
                title = title,
                platform = platform,
                releaseDate = releaseDate
            )
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    repo.insert(game)
                    withContext(Dispatchers.Main) {
                        success.value = true
                    }
                }
            }
        }
    }

    fun removeGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                repo.remove(game)
            }
        }
    }

    fun removeAll() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                repo.removeAll()
            }
        }
    }

    private fun gameIsValid(
        title: String,
        platform: String,
        day: Int,
        month: Int,
        year: Int
    ): Boolean {
        var maxDay = 31
        if (month in 1..12) {
            val cal = Calendar.getInstance()
            cal.set(year, month - 1, 1)
            maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        }
        return when {
            title.isBlank() -> {
                error.value = "Title is required"
                false
            }
            platform.isBlank() -> {
                error.value = "Platform is required"
                false
            }
            year < 1 -> {
                error.value = "Year must be a positive number"
                false
            }
            month < 1 || month > 12 -> {
                error.value = "Month must be between 1 and 12"
                false
            }
            day < 1 || day > maxDay -> {
                error.value = "Day is invalid for given month"
                false
            }
            else -> true
        }
    }
}
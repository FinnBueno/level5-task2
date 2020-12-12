package com.peakfinn.madlevel5task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peakfinn.madlevel5task2.data.Game
import kotlinx.android.synthetic.main.game_item.view.*

/**
 * Created by Finn Bon on 12/12/2020.
 */
class GameAdapter(
    private val games: List<Game>
): RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.title.text = game.title
            itemView.platform.text = game.platform
            itemView.release.text = "Release: ${game.releaseDate}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game_item, parent, false)
        )
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

}
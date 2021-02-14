package hr.fer.ruzaosa.projekt.ruzaosa.memory.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.User
import kotlinx.android.synthetic.main.users.view.*

class PlayersAdapter (val players : List<User>, val listener : OnPlayerClickListener) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.users, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]

        holder.view.FirstAndLastName.text = player.firstName +" "+ player.lastName
        holder.view.usernamePlayer.text = player.username
        holder.imageView.setImageResource(R.drawable.active_player)

    }

    override fun getItemCount() = players.size

    inner class PlayerViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
            val imageView: ImageView = itemView.player_icon
            //val textViewName: TextView = itemView.FirstAndLastName
            //val textViewUsername: TextView = itemView.usernamePlayer

            init {
                itemView.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onPlayerClick(position)
                }
            }

        }

        interface OnPlayerClickListener {
            fun onPlayerClick(position: Int)
        }
    }


package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import kotlinx.android.synthetic.main.users.view.*

class PlayersAdapter (val players : List<User>) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.users, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = players[position]
        holder.view.FirstAndLastName.text = player.firstName + player.lastName
        holder.view.usernamePlayer.text = player.username


    }

    override fun getItemCount() = players.size

    class PlayerViewHolder (val view: View) : RecyclerView.ViewHolder(view)

}
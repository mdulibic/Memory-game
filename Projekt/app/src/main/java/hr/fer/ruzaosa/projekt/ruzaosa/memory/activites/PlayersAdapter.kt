package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import kotlinx.android.synthetic.main.users.view.*

/*class PlayersAdapter (val players : List<User>) : RecyclerView.Adapter<PlayersAdapter.PlayerViewHolder>(){


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
}*/
class PlayersAdapter (val players : List<User>): RecyclerView.Adapter<PlayersAdapter.ViewHolder>(), View.OnClickListener {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var firstAndLastName: TextView? = null
        var username: TextView? = null
        init {
            firstAndLastName = itemView.findViewById(R.id.exitActivePlayers) // možda
            //on  u onom primejru pristupa ovako slično ali mislim da nama ovo neće raditi
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val playersListElement = inflater.inflate(R.layout.users, parent, false)
        playersListElement.setOnClickListener(this)
        return ViewHolder(playersListElement)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = players[position]
        holder.firstAndLastName?.text = player.firstName + player.lastName
        holder.username?.text = player.username
    }
    override fun onClick(v: View?) {
        Log.d("tag","Hello");
    } //treba li nam ovo uopće, tj. što ovo radi
}
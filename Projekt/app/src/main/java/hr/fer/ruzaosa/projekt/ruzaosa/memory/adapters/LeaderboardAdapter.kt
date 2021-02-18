package hr.fer.ruzaosa.projekt.ruzaosa.memory.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.ScoreData
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.User

public class LeaderboardAdapter(private val context: Context,
                                private val dataSource: Array<ScoreData>
) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    //1
    override fun getCount(): Int {
        return dataSource.size
    }

    //2
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    //4
    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.leaderboard_row, parent, false)

        val username = rowView.findViewById(R.id.usernameLeaderboard) as TextView
        val rankButton = rowView.findViewById(R.id.rankBtn) as Button
        val noOfWins = rowView.findViewById(R.id.victoriesLeaderboard) as TextView

        val score = getItem(position) as ScoreData

        if (score.rank == 1) rankButton.setBackgroundColor(ContextCompat.getColor(context, R.color.gold))
        if (score.rank == 2) rankButton.setBackgroundColor(ContextCompat.getColor(context, R.color.silver))
        if (score.rank == 3) rankButton.setBackgroundColor(ContextCompat.getColor(context, R.color.bronze))

        username.text = score.playerUsername
        rankButton.text = score.rank.toString()
        noOfWins.text = score.wins.toString()

        return rowView
    }

}
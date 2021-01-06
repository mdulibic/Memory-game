package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import kotlinx.android.synthetic.main.activity_active_players.*

public class ActivePlayersActivity : AppCompatActivity() {

//    var prefs: SharedPreferences = applicationContext.getSharedPreferences("UserPrefs",
//                                    Context.MODE_PRIVATE)

    lateinit var activePlayersList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)

//        activePlayersList = ArrayList<String>()
//        var username = prefs.getString("Username", "")
//        activePlayersList.add(username!!)
//        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
//                this@ActivePlayersActivity,
//                android.R.layout.simple_list_item_1,
//                activePlayersList as List<String?>
//        )
//        activePlayersListView.adapter = adapter

        exitActivePlayers.setOnClickListener{ finish() }
    }
}
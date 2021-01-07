package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import kotlinx.android.synthetic.main.activity_active_players.*

public class ActivePlayersActivity : AppCompatActivity() {

//    var prefs: SharedPreferences = applicationContext.getSharedPreferences("UserPrefs",
//                                    Context.MODE_PRIVATE)
    // pokušavam pushati komentar

    lateinit var activePlayers: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)

          activePlayers = ArrayList<String>()
//        var username = prefs.getString("Username", "")
//        activePlayersList.add(username!!)
        activePlayers.add("username1")
        activePlayers.add("username2")
        activePlayers.add("username3")
        activePlayers.add("username4")
        activePlayers.add("username5")
        activePlayers.add("username6")
        activePlayers.add("username7")
        activePlayers.add("username8")
        activePlayers.add("username9")
        activePlayers.add("username10")
        activePlayers.add("username11")
        activePlayers.add("username12")
        activePlayers.add("username13")
        activePlayers.add("username14")
        activePlayers.add("username15")

        val itemsAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activePlayers)
        activePlayersList.adapter = itemsAdapter

        activePlayersList.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, i, l ->
                    val value: String = adapterView.getItemAtPosition(i) as String
                    val intent = Intent(this, ChallengeActivity::class.java)
                    intent.putExtra("challengedUser", value)
                    intent.putExtra("role", "host")
                    startActivity(intent)
                }
        simulBtn.setOnClickListener {
            val intent = Intent(this, ChallengeActivity::class.java)
            intent.putExtra("challengedUser", "me")
            intent.putExtra("role", "guest")
            startActivity(intent)
        }
        exitActivePlayers.setOnClickListener { finish() }
    }
}
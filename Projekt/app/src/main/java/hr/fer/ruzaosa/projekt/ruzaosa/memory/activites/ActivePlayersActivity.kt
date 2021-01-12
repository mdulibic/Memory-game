package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.LogInActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameBody
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameService
import kotlinx.android.synthetic.main.activity_active_players.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



public class ActivePlayersActivity : AppCompatActivity() {
    val PREFS = "MyPrefsFile"
    val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
    val challenger = prefs.getString("username", "No name defined")
    var users:MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)
        var activePlayersList =findViewById<ListView>(R.id.activePlayersList)
        var playersList=getListOfActivePlayers()
        var activePlayers=arrayOfNulls<String>(playersList.size)
        for (i in activePlayers.indices)
           activePlayers[i]=playersList[i]


        val itemsAdapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activePlayers)
        activePlayersList.adapter = itemsAdapter

        activePlayersList.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val challengedUser: String = adapterView.getItemAtPosition(i) as String
                val player1=User("","", challenger.toString(),"","","")
                val player2=User("","",challengedUser,"","","")
                var players= GameBody(player1,player2)
                val intent = Intent(this, WaitRoomActivity::class.java)
                sendNotifToChallenged(players)
            }

        exitActivePlayers.setOnClickListener { finish() }
    }

    private fun sendNotifToChallenged(players:GameBody) {
        val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
        retIn.sendNotifToChallenged(players).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                        this@ActivePlayersActivity,
                        "Unknown error!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    startActivity(intent)
                }
            }
        })
    }

    private fun getListOfActivePlayers(): List<String> {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        retIn.getUsersList().enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@ActivePlayersActivity,
                    "Unknown error!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                 users= response.body() as MutableList<String>
                }
            }
        })
      return users

    }
}

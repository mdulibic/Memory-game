package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import hr.fer.ruzaosa.projekt.ruzaosa.memory.adapters.PlayersAdapter
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.GameBody
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameService
import kotlinx.android.synthetic.main.activity_active_players.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class ActivePlayersActivity : AppCompatActivity(), PlayersAdapter.OnPlayerClickListener {

    val PREFS="MyPrefsFile"
    lateinit var prefs:SharedPreferences
    var users: List<User> = arrayListOf()
    lateinit var challengerUsername : String

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        challengerUsername = prefs.getString("username", "No name defined")!!
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)
        getListOfActivePlayers()
        exitActivePlayers.setOnClickListener {
            finish()
        }
    }
    override fun onPlayerClick(position: Int) {
        val challenged = PlayersAdapter(users,this).players.get(position)
        var challenger: User
        var players: GameBody
        for (i in users.indices) {
            if (users[i].username == challengerUsername) {
                challenger = users[i]
                players = GameBody(challenger, challenged)
                if(challenger.username == challenged.username){
                    Toast.makeText(
                            this@ActivePlayersActivity,
                            "You have entered practice mode! Good luck! :)",
                            Toast.LENGTH_LONG
                    ).show()
                    val myIntent = Intent(this, GameActivity::class.java)
                    startActivity(myIntent)
                }else {
                    val intent = Intent(this@ActivePlayersActivity, WaitRoomActivity::class.java)
                    intent.putExtra("challenged", challenged.username)
                    startActivity(intent)
                    initalizeGame(players)
                    sendNotifToChallenged(players)
                }
            }
        }
    }

//dohvat liste igrača od kojih biramo igrača kojeg želimo izazvati na igru
    private fun getListOfActivePlayers() {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        retIn.getUsersList().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(
                        this@ActivePlayersActivity,
                        "Unknown error!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>
            ) {
                if (response.code() == 200) {
                    users= response.body()!!
                    Log.d("tag","List successfully reached!")
                    recyclerViewPlayers.layoutManager = LinearLayoutManager(this@ActivePlayersActivity)
                    recyclerViewPlayers.adapter = PlayersAdapter(users,this@ActivePlayersActivity)

                }
            }


        })
    }

 //inicijalizacija igre se odvija odma pri slanja zahtjeva za igru bez obzira održi li se igra ili ne
    private fun initalizeGame(players: GameBody) {
        val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
        retIn.createGame(players).enqueue(object : Callback<Long> {
            override fun onFailure(call: Call<Long>, t: Throwable) {
                Toast.makeText(
                        this@ActivePlayersActivity,
                        "Unknown error!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                    call: Call<Long>,
                    response: Response<Long>
            ) {
                if (response.code() == 200) {
                    Log.d("tag","Game initialized")
                    var gameId = response.body()
                    val editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit()
                    editor.putLong("gameId", gameId!!)
                    editor.apply()
                }
            }
        })
    }

//slanje zahtjeva za igru odabranom igraču
    private fun sendNotifToChallenged(players: GameBody) {
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
                    Log.d("tag","Notif sent!")
                }
            }
        })
    }




}

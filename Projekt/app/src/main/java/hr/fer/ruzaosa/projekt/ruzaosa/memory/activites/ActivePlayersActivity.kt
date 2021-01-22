package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.ruzaosa.lecture4.ruzaosa.R
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
    val PREFS="MyPrefsFile"
    lateinit var prefs:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val challenger = prefs.getString("username", "No name defined")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)
        getListOfActivePlayers()


                //val challengedUser = adapterView.getItemAtPosition(i) as User
                //val challenger=User("","", challenger.toString(),"","","", 0)
                //val challenged=User("","",challengedUser.username,"","","", 0)
                //var players= GameBody(challenger,challenged, 0L) // treba promijeniti u pravi gameId!!!
                //val intent = Intent(this, WaitRoomActivity::class.java)
                //intent.putExtra("challenged",challenged.token)
                //initalizeGame(players)
                //sendNotifToChallenged(players)


        exitActivePlayers.setOnClickListener { finish() }
    }

    private fun initalizeGame(players:GameBody) {
        val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
        retIn.createGame(players).enqueue(object : Callback<ResponseBody> {
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
                    var game=response.body() as GameBody
                    val editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit()
                    editor.putLong("gameId", game.gameId)
                    editor.apply()
                }
            }
        })
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

    private fun getListOfActivePlayers() {

        var users: List<User> = arrayListOf()
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
                        users= response!!.body()!!

                    recyclerViewMovies.layoutManager = LinearLayoutManager(this@ActivePlayersActivity)
                    recyclerViewMovies.adapter = PlayersAdapter(users)

                }
            }


        })
    }
}
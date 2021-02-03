package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var recyclerView : RecyclerView
    var users: List<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val challengerUsername = prefs.getString("username", "No name defined")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)
        recyclerView = findViewById(R.id.recyclerViewPlayers)
        getListOfActivePlayers()

        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(this, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(view: View, position: Int) {
                var challenged = PlayersAdapter(users).players.get(position)
                var challenger:User
                var players:GameBody
                for(i in users.indices){
                    if(users[i].username==challengerUsername){
                        challenger=users[i]
                        players=GameBody(challenger, challenged, 0L)
                        val intent = Intent(this@ActivePlayersActivity, WaitRoomActivity::class.java)
                        intent.putExtra("challenged",challenged.token)
                        initalizeGame(players)
                        sendNotifToChallenged(players)


                    }

                }


            }
        }))



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

                    recyclerViewPlayers.layoutManager = LinearLayoutManager(this@ActivePlayersActivity)
                    recyclerViewPlayers.adapter = PlayersAdapter(users)

                }
            }


        })
    }
    }

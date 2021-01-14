package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Intent
import android.content.SharedPreferences
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
    val PREFS="MyPrefsFile"
    lateinit var prefs:SharedPreferences
    var users:List<User> = listOf()
    lateinit var activePlayersList:ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val challenger = prefs.getString("username", "No name defined")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)
        activePlayersList =findViewById<ListView>(R.id.activePlayersList)
        users = getListOfActivePlayers()
        var list= mutableListOf<String>()
        for (i in users.indices)
           list[i]=users[i].username

        val itemsAdapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        activePlayersList.adapter = itemsAdapter

        activePlayersList.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, i, l ->
                val challengedUser = adapterView.getItemAtPosition(i) as User
                val player1=User("","", challenger.toString(),"","","", 0)
                val player2=User("","",challengedUser.username,"","","", 0)
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

    private fun getListOfActivePlayers(): List<User> {

        var test: MutableList<User> = arrayListOf()
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
                    test.addAll(response.body()!!)
                }
            }
        })
        return test
    }
}

package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.LogInActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import kotlinx.android.synthetic.main.activity_active_players.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class ActivePlayersActivity : AppCompatActivity() {
    val PREFS = "MyPrefsFile"
    lateinit var activePlayers: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)

          activePlayers = ArrayList<String>()
/*
        val usersList: Array<out String>? = intent.extras?.getStringArray("users")
        activePlayers.addAll(usersList!!)
L */


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
                    val challengedUser: String = adapterView.getItemAtPosition(i) as String
                    val intent = Intent(this, WaitRoomActivity::class.java)
                    intent.putExtra("challengedUser", challengedUser)//username izazvanog
                    val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
                    val challenger = prefs.getString("name", "No name defined")
                    intent.putExtra("challengerName",challenger)//username izazivaca
                    sendNotifToChallenged(challengedUser)
                    startActivity(intent)
                }

        exitActivePlayers.setOnClickListener { finish() }
    }

    private fun sendNotifToChallenged(challengedUser: String) {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        var challenged= User("","",challengedUser,"","","")
        retIn.registerUser(registerInfo).enqueue(object :
                Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                btnRegister.isEnabled = true
                btnRegister.text = "REGISTER"
                Toast.makeText(
                        this@RegistrationActivity,
                        t.message,
                        Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    Toast.makeText(this@RegistrationActivity, "Registration success!", Toast.LENGTH_SHORT)
                            .show()

                    startActivity(Intent(this@RegistrationActivity, LogInActivity::class.java))
                } else {
                    btnRegister.isEnabled = true
                    btnRegister.text = "REGISTER"
                    Toast.makeText(this@RegistrationActivity, "Registration failed!", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }
    }
}
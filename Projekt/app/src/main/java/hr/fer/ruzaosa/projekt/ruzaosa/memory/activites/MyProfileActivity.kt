package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.LogInBody
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.User
import kotlinx.android.synthetic.main.activity_my_profile.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        val float = AnimationUtils.loadAnimation(this, R.anim.floating)
        val username = intent.extras?.get("myUsername").toString()

        myProfileTxt.startAnimation(float)
        usernameDisplayTxt.text = username.toString()
        profileimg.setBackgroundResource(R.drawable.roundbutton)
        getWins(username)
        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun getWins(username: String) {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        val user= User(0,"","", username,"","","", 0)
        retIn.getWins(user).enqueue(object : Callback<Long> {
            override fun onFailure(call: Call<Long>, t: Throwable) {
                Toast.makeText(
                        this@MyProfileActivity,
                        "Unknown error!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                    call: Call<Long>,
                    response: Response<Long>
            ) {
                if (response.code() == 200) {
                    Log.d("tag", "Wins fetched!")
                    victoriesTextView.text = response.body().toString()
                }
            }


        })
    }
}


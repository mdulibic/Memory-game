package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.GameBody
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameService
import kotlinx.android.synthetic.main.activity_challenge.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    lateinit var challenger : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)
        challenger= intent?.getStringExtra("challenger")!!
        usernameText.setText(challenger)
        textView.setText("challenged you!")
        progressBar2.visibility = View.INVISIBLE
        buttonAccept.setOnClickListener {
            gameAccepted()
            val myIntent = Intent(this@ChallengeActivity, GameActivity::class.java)
            startActivity(myIntent)
        }
        buttonReject.setOnClickListener {
            gameRejected()
            finish()
        }
    }

    private fun gameRejected() {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        retIn.gameRejected(challenger).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                        this@ChallengeActivity,
                        "Unknown error!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    Log.d("tag","Game rejected!")
                }
            }
        })
    }


    private fun gameAccepted() {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        retIn.gameAccepted(challenger).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                        this@ChallengeActivity,
                        "Unknown error!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    Log.d("tag","Game accepeted!")
                }
            }
        })
    }
}




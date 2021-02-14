package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.GameBody
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameService
import kotlinx.android.synthetic.main.activity_challenge.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChallengeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
    val PREFS="MyPrefsFile"
    lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)
        val challenger=intent?.getStringExtra("challenger")
        usernameText.setText(challenger)
        textView.setText("challenged you!")
        progressBar2.visibility = View.INVISIBLE
        buttonAccept.setOnClickListener {
            gameAccepted()
        }
        buttonReject.setOnClickListener {
            gameRejected()
            finish()
        }
    }

    private fun gameRejected() {
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val gameId = prefs.getLong("gameId", 0L)
        val game= GameBody(null,null,gameId)
        val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
        retIn.gameRejected(game).enqueue(object : Callback<ResponseBody> {
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
                    val myIntent = Intent(this@ChallengeActivity, MenuActivity::class.java)
                    this@ChallengeActivity.startActivity(myIntent)
                }
            }
        })
    }


    private fun gameAccepted() {
        prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val gameId = prefs.getLong("gameId", 0L)
        val game= GameBody(null,null,gameId)
        val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
        retIn.gameAccepted(game).enqueue(object : Callback<ResponseBody> {
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
                    val myIntent = Intent(this@ChallengeActivity, GameActivity::class.java)
                    this@ChallengeActivity.startActivity(myIntent)
                }
            }
        })
    }
}




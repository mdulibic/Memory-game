package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameBody
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameService
import kotlinx.android.synthetic.main.activity_challenge.*

class ChallengeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)

        usernameText.setText("")
        textView.setText("challenged you!")
        progressBar2.visibility = View.INVISIBLE
        buttonAccept.setOnClickListener {
            gameAccepted()
            val myIntent = Intent(this@ChallengeActivity, GameActivity::class.java)
            this@ChallengeActivity.startActivity(myIntent)
        }
        buttonReject.setOnClickListener {
            gameRejected()
            finish()
        }
    }

    private fun gameRejected() {
      //kad stisne reject, salje se challengeru notif da neće igrat i vrati im se na MenuActivity
    }

    private fun gameAccepted() {
     //kad stisne accept, šalje se challengeru notif da će igrat i idu u GameActivity+Rest poziv za kreiranje igre
    }
}




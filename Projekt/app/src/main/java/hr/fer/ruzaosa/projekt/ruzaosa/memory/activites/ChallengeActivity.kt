package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import kotlinx.android.synthetic.main.activity_challenge.*

class ChallengeActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)

        val challengedUser = intent.extras?.getString("challengedUser")

                usernameText.setText("")
                textView.setText("challenged you!")
                progressBar2.visibility = View.INVISIBLE
                buttonAccept.setOnClickListener {
                    val myIntent = Intent(this@ChallengeActivity, GameActivity::class.java)
                    this@ChallengeActivity.startActivity(myIntent)
                }
                buttonReject.setOnClickListener {
                    finish()
                }
            }

}





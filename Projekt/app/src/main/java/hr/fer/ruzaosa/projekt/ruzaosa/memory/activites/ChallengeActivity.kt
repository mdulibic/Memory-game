package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.LogInActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import kotlinx.android.synthetic.main.activity_challenge.*

class ChallengeActivity : AppCompatActivity() {

    val buttonAccept = findViewById<Button>(R.id.buttonAccept)
    val buttonReject = findViewById<Button>(R.id.buttonReject)

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)

        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        usernameText.setText("")
        textView.setText("challenged you!")
        progressBar2.visibility = View.INVISIBLE
        buttonAccept.setOnClickListener {
                    startActivity(Intent(this@ChallengeActivity, GameActivity::class.java))
                    //pozivanje rest za kreiranje igre
                }
        buttonReject.setOnClickListener {
                    startActivity(Intent(this@ChallengeActivity, MenuActivity::class.java))
                }
            }
        }




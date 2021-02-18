package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.ActivePlayersActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.GameActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.LeaderboardActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.MyProfileActivity


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        val match=intent?.getStringExtra("match")
        if(match!=null){
            Toast.makeText(
                    this@MenuActivity,
                    "You have lost the game! Better luck next time! ;)",
                    Toast.LENGTH_LONG
            ).show()
        }
        val float = AnimationUtils.loadAnimation(this, R.anim.floating)
        val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)

        setContentView(R.layout.activity_menu)
        val profileimg = findViewById<ImageView>(R.id.profileimg)
        val leaderboardimg = findViewById<ImageView>(R.id.leaderboardimg)
        val playimg = findViewById<ImageView>(R.id.playimg)
        val menutxt = findViewById<TextView>(R.id.menutxt)
        val extras = intent.extras

        profileimg.setBackgroundResource(R.drawable.roundbutton)
        leaderboardimg.setBackgroundResource(R.drawable.roundbutton)
        playimg.setBackgroundResource(R.drawable.roundbutton)

        menutxt.startAnimation(float)
        playimg.setOnClickListener {
            playimg.startAnimation(pulse)
                startActivity(Intent(this@MenuActivity, ActivePlayersActivity::class.java))
        }
        leaderboardimg.setOnClickListener { leaderboardimg.startAnimation(pulse) ; startActivity(Intent(this@MenuActivity, LeaderboardActivity::class.java))}
        profileimg.setOnClickListener { profileimg.startAnimation(pulse)
            val myIntent = Intent(this@MenuActivity, MyProfileActivity::class.java)
            val myusername: String? = extras?.getString("myUsername")
            myIntent.putExtra("myUsername", myusername)
            this@MenuActivity.startActivity(myIntent)}

    }
}
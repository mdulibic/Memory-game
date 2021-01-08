package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.ActivePlayersActivity


class MenuActivity : AppCompatActivity() {

//var prefs: SharedPreferences = applicationContext.getSharedPreferences("UserPrefs",
//        Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) { }

        val float = AnimationUtils.loadAnimation(this, R.anim.floating)
        val pulse = AnimationUtils.loadAnimation(this, R.anim.pulse)

        setContentView(R.layout.activity_menu)
        val profileimg = findViewById<ImageView>(R.id.profileimg)
        val leaderboardimg = findViewById<ImageView>(R.id.leaderboardimg)
        val playimg = findViewById<ImageView>(R.id.playimg)
        val menutxt=findViewById<TextView>(R.id.menutxt)
        val activityswitch=findViewById<Switch>(R.id.active_switch)
        profileimg.setBackgroundResource(R.drawable.roundbutton)
        leaderboardimg.setBackgroundResource(R.drawable.roundbutton)
        playimg.setBackgroundResource(R.drawable.roundbutton)

/*
        val usersList: Array<out String>? = intent.extras?.getStringArray("users")
        val usersList2: ArrayList<String> = arrayListOf()
        usersList2.addAll(usersList!!)
L */
        menutxt.startAnimation(float)
        playimg.setOnClickListener {

            playimg.startAnimation(pulse)
            Handler().postDelayed({
                val myIntent = Intent(this@MenuActivity, ActivePlayersActivity::class.java)
/*
                myIntent.putExtra("users", usersList2)
L */
                this@MenuActivity.startActivity(myIntent)
            }, 400)
        }
        leaderboardimg.setOnClickListener { leaderboardimg.startAnimation(pulse) }
        profileimg.setOnClickListener { profileimg.startAnimation(pulse) }

        activityswitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                activityswitch.text="ACTIVE"
            }else{
                activityswitch.text="INACTIVE"
            }
        }
    }
}
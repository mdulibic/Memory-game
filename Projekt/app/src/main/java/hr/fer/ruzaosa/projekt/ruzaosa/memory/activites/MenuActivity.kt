package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import  android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_menu.leaderboardimg
import kotlinx.android.synthetic.main.activity_menu.playimg
import kotlinx.android.synthetic.main.activity_menu.profileimg



class MenuActivity : AppCompatActivity() {

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

        profileimg.setBackgroundResource(R.drawable.roundbutton)
        leaderboardimg.setBackgroundResource(R.drawable.roundbutton)
        playimg.setBackgroundResource(R.drawable.roundbutton)

        menutxt.startAnimation(float)
        playimg.setOnClickListener { playimg.startAnimation(pulse) }
        leaderboardimg.setOnClickListener { leaderboardimg.startAnimation(pulse) }
        profileimg.setOnClickListener { profileimg.startAnimation(pulse) }

    }
}
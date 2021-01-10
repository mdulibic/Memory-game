package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import kotlinx.android.synthetic.main.activity_my_profile.*

class MyProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)

        val float = AnimationUtils.loadAnimation(this, R.anim.floating)
        val username = intent.extras?.get("myUsername")

        myProfileTxt.startAnimation(float)
        usernameDisplayTxt.text = username.toString()
        profileimg.setBackgroundResource(R.drawable.roundbutton)

        backBtn.setOnClickListener {
            finish()
        }
    }
}
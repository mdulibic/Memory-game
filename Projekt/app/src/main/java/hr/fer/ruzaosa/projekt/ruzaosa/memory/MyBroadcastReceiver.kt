package hr.fer.ruzaosa.projekt.ruzaosa.memory

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.ChallengeActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.GameActivity

class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        if(message == "Call for play") {
            val i = Intent(context, ChallengeActivity::class.java)
            val challenger=intent?.getStringExtra("challenger")
            i.putExtra("challenger",challenger)
            context?.startActivity(i)
        }
        if(message == "Challenged accepted") {
            val i = Intent(context, GameActivity::class.java)
            context?.startActivity(i)
        }
        if(message == "Challenged rejected") {
            val i = Intent(context, MenuActivity::class.java)
            context?.startActivity(i)
        }
        if(message == "Notif for loser") {
            val i = Intent(context, MenuActivity::class.java)
            i.putExtra("match","You have lost!")
            context?.startActivity(i)
        }
        if(message == "Game canceled") {
            val i = Intent(context, MenuActivity::class.java)
            context?.startActivity(i)
        }

    }
}
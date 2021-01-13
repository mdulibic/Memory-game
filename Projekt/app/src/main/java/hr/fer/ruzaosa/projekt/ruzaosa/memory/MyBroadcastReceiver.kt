package hr.fer.ruzaosa.projekt.ruzaosa.memory

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.ChallengeActivity

class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        if(message == "Call for play"){
            val i = Intent(context, ChallengeActivity::class.java)
            context?.startActivity(i)
        }
    }
}
package hr.fer.ruzaosa.projekt.ruzaosa.memory

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.ChallengeActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.activites.GameActivity

class MyBroadcastReceiver : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        if(message == "Call for play"){
            val i = Intent(context, ChallengeActivity::class.java)
            context?.startActivity(i)
        }
        if(message == "Challenged accepted") {
            val i = Intent(context, GameActivity::class.java)
            context?.startActivity(i)
        }
        if(message == "Challenged rejected") {
            // pošalji notification Challengeru da ga je Challenged odbio :(
            // vrati Challengera na MenuActivity
            val i = Intent(context, MenuActivity::class.java)
            context?.startActivity(i)
        }
        if(message == "Game canceled") {
            // Challenger je ipak odustao od challengenja
            // šalje se notification Challengedu da ipak ništa od igre :(
            // vraćamo li tu onda i Challengeda na Menu?
            val i = Intent(context, MenuActivity::class.java)
            context?.startActivity(i)
        }
        if(message == "") {

        }
    }
}
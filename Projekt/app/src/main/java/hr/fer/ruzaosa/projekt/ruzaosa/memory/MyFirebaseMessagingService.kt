package hr.fer.ruzaosa.projekt.ruzaosa.memory

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService


class MyFirebaseMessagingService: FirebaseMessagingService() {
    val PREFS = "MyPrefsFile"
    val broadcastString = "fer.hr.ruazosa.memory"
    val intent = Intent(broadcastString)

    override fun onMessageReceived(remoteMessage: RemoteMessage) {//kad primimo poruku ovdje je handleamo

        if(remoteMessage.data.containsKey("Call for play")){
            intent.putExtra("message", "Call for play")
            intent.putExtra("challenger",remoteMessage.data.get("challenger"))
            sendBroadcast(intent)
        }
        else if(remoteMessage.data.containsKey("Challenged accepted")){
            intent.putExtra("message", "Challenged accepted")
            sendBroadcast(intent)
        }
        else if(remoteMessage.data.containsKey("Challenged rejected")){
            intent.putExtra("message", "Challenged rejected")
            sendBroadcast(intent)
        }
        else if(remoteMessage.data.containsKey("Game cancelled")){
            intent.putExtra("message", "Game cancelled")
            sendBroadcast(intent)
        }
        else if(remoteMessage.data.containsKey("Game lost")){
            intent.putExtra("message", "")
            sendBroadcast(intent)
        }
        else if(remoteMessage.data.containsKey("Notif for loser")){
            intent.putExtra("message", "")
            sendBroadcast(intent)
        }
    }




    override fun onNewToken(token: String) {
        Log.d("tag", "Refreshed token: $token")
        updateToken(token)
    }

    private fun updateToken(token: String) {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val username = prefs.getString("username", "No name defined").toString() //"No name defined" is the default value
        val update= User("","", username,"","",token, 0)
        retIn.updateToken(update)
    }

}
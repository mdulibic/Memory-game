package hr.fer.ruzaosa.projekt.ruzaosa.memory

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService


class MyFirebaseMessagingService: FirebaseMessagingService() {
    val PREFS = "MyPrefsFile"
    val broadcastString = "fer.hr.ruazosa.memory"
    val intent = Intent(broadcastString)

    override fun onMessageReceived(remoteMessage: RemoteMessage) {//kad primimo poruku ovdje je handleamo

        if(remoteMessage.data.containsKey("Call for play")){
            intent.putExtra("message", "Call for play")
            sendBroadcast(intent)
            // kada se naprave push notifikacije njihovi keyevi stavljaju se u ""
        } else if(remoteMessage.data.containsKey("Challenged accepted")){
            intent.putExtra("message", "Challenged accepted")
            sendBroadcast(intent)
        } else if(remoteMessage.data.containsKey("Challenged rejected")){
            // vrati se na MenuActivity
            // pošalji Challengeru notification da je Challenged odbio?
            intent.putExtra("message", "Challenged rejected")
            sendBroadcast(intent)
        } else if(remoteMessage.data.containsKey("Game canceled")){
            intent.putExtra("message", "Game canceled")
            sendBroadcast(intent)
        } else if(remoteMessage.data.containsKey("")){
            intent.putExtra("message", "")
            sendBroadcast(intent)
        }
    }




    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        updateToken(token)
    }

    private fun updateToken(token: String) {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val username = prefs.getString("username", "No name defined").toString() //"No name defined" is the default value
        val update= User("","", username,"","",token, 0)
        retIn.updateToken(update)
    }


    companion object {
        private const val TAG = "MyFirebaseMsgService"
        var PLAYER_CHALLENGED = "The second player has been challenged"
        var GAME_ACCEPTED = "The second player accepted the game"
        var GAME_REJECTED = "The second player rejected the game"
        var LOSER = "You have lost the game"
        var CANCELED_GAME = "The challenger canceled the game"
    }
}
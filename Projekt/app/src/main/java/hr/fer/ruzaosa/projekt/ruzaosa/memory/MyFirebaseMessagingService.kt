package hr.fer.ruzaosa.projekt.ruzaosa.memory

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService


class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {//kad primimo poruku ovdje je handleamo
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            // TODO broadcast event that message is received
        }
    }


    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        updateToken(token)
    }

    private fun updateToken(token: String) {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        retIn.updateUserToken(token)
    }


    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}
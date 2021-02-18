package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.projekt.ruzaosa.memory.MyBroadcastReceiver


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var broadcast : MyBroadcastReceiver = MyBroadcastReceiver()
        val filter = IntentFilter().apply {
            addAction("fer.hr.ruazosa.memory")
        }
        registerReceiver(broadcast, filter)

        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
        finish()
    }
}

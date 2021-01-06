package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import kotlinx.android.synthetic.main.activity_active_players.*

public class ActivePlayersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_players)

        exitActivePlayers.setOnClickListener{ finish() }
    }
}
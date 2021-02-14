package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import hr.fer.ruzaosa.projekt.ruzaosa.memory.adapters.LeaderboardAdapter
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.ScoreData
import kotlinx.android.synthetic.main.activity_leaderboard.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaderboardActivity : AppCompatActivity() {

    var scoreDataList: MutableList<ScoreData> = mutableListOf()
    var scoreDataArray: Array<ScoreData> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        updateScoreDataList()

        val adapter = LeaderboardAdapter(this, scoreDataList.toTypedArray())
        leaderboardListView.adapter = adapter

    }

    private fun updateScoreDataList() {
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        retIn.getUsersList().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(
                    this@LeaderboardActivity,
                    "Unknown error!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                call: Call<List<User>>,
                response: Response<List<User>>
            ) {
                if (response.code() == 200) {
                    val usersList = response.body()!!
                    var rankCounter = 1
                    var previousWinCount = -1

                    usersList.forEach {if (it.wins == null) it.wins = 0 }
                    usersList.sortedBy { it.wins }
                    usersList.forEach {
                        if (previousWinCount != -1 && previousWinCount != it.wins)  // ako je iduca pobjeda drukcija od prosle povecavamo mjesto
                            scoreDataList.add(ScoreData(it.username, it.wins, rankCounter++))
                        else
                            scoreDataList.add(ScoreData(it.username, it.wins, rankCounter))
                    }

                }
            }
        })
    }
}
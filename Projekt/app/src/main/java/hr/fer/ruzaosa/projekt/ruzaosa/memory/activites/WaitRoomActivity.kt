package hr.fer.ruzaosa.projekt.ruzaosa.memory.activites

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.activites.MenuActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameService
import kotlinx.android.synthetic.main.activity_waitroom.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WaitRoomActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waitroom)

        buttonCancel.setOnClickListener{
            cancelGameRequest()
        }
    }

    private fun cancelGameRequest() {
        val token=intent?.getStringExtra("challenged")
        val retIn = RetrofitInstance.getRetrofit().create(GameService::class.java)
        retIn.gameCancelled(token!!).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                        this@WaitRoomActivity,
                        "Unknown error!",
                        Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
            ) {
                if (response.code() == 200) {
                    val myIntent = Intent(this@WaitRoomActivity, MenuActivity::class.java)
                    this@WaitRoomActivity.startActivity(myIntent)
                }
            }
        })
    }
}

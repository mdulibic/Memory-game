package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val username1 = findViewById<EditText>(R.id.usernameRegistration)
        val firstName1 = findViewById<EditText>(R.id.firstName)
        val lastName1 = findViewById<EditText>(R.id.lastName)
        val email1 = findViewById<EditText>(R.id.emailRegistration)
        val password1 = findViewById<EditText>(R.id.passwordRegistration)
        val btnRegister = findViewById<Button>(R.id.registerButton)

        btnRegister.setOnClickListener {
            val firstName = firstName1.text.toString()
            val lastName = lastName1.text.toString()
            val username = username1.text.toString()
            val email = email1.text.toString()
            val password = password1.text.toString()
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }
                val token = task.result.toString()
                signup(firstName,lastName,username,email,password,token)
            })


        }
    }

    private fun signup(firstName: String, lastName: String, username: String,
                      email: String, password: String,token :String){

        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        val registerInfo = User(firstName,lastName,username,email,password,token)

        retIn.registerUser(registerInfo).enqueue(object :
            Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(
                    this@RegistrationActivity,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    Toast.makeText(this@RegistrationActivity, "Registration success!", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@RegistrationActivity,LogInActivity::class.java))
                }
                else{
                    Toast.makeText(this@RegistrationActivity, "Registration failed!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
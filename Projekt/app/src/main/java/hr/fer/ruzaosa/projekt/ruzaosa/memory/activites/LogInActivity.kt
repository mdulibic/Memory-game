package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.LogInBody
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LogInActivity : AppCompatActivity() {
    val PREFS = "MyPrefsFile"
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var btnLogin: Button
    lateinit var register: TextView

    // dupli preferences
    // var prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    // var editor: SharedPreferences.Editor = prefs.edit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById<EditText>(R.id.usernameEditText)
        password = findViewById<EditText>(R.id.passwordEditText)
        btnLogin = findViewById(R.id.buttonLogin)
        register = findViewById(R.id.registerTextView)


        btnLogin.setOnClickListener {
            btnLogin.isClickable = false
            if (TextUtils.isEmpty(username.text.toString()) || TextUtils.isEmpty(password.text.toString())) {
                btnLogin.isClickable = true
                Toast.makeText(
                        this@LogInActivity,
                        "Username / Password Required",
                        Toast.LENGTH_LONG
                ).show()
            } else {
                // pokusala poslat prefs
//                editor.putString("Username", username.text.toString())
//                editor.putString("ActivityStatus", "active")
//                editor.commit()

                val username = username.text.toString()
                val password = password.text.toString()
                login(username, password)
            }
        }
        register.setOnClickListener {
            register.isClickable = false
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
        private fun login(username: String, password: String) {
            val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
            val logInInfo = LogInBody(username, password)
            retIn.login(logInInfo).enqueue(object : Callback<ResponseBody> {

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                            this@LogInActivity,
                            t.message,
                            Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(this@LogInActivity, "Login success!", Toast.LENGTH_SHORT)
                                .show()
                        val editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit()
                        editor.putString("username", username)
                        editor.apply()
                        startActivity(Intent(this@LogInActivity, MenuActivity::class.java))

                    } else {
                        Toast.makeText(this@LogInActivity, "Login failed!", Toast.LENGTH_SHORT)
                                .show()
                    }
                }
            })
        }

    }

package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.LogInBody
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class LogInActivity : AppCompatActivity() {
    val PREFS = "MyPrefsFile"
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var btnLogin: Button
    lateinit var register: TextView
    var usersSet: MutableSet<String> = TreeSet()
    var usersList: ArrayList<String> = arrayListOf()
    // dupli preferences
    // var prefs = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    // var editor: SharedPreferences.Editor = prefs.edit()

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("message")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        username = findViewById<EditText>(R.id.usernameEditText)
        password = findViewById<EditText>(R.id.passwordEditText)
        btnLogin = findViewById(R.id.buttonLogin)
        register = findViewById(R.id.registerTextView)

/*
        myRef.root.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                updateUsers(dataSnapshot)
                usersList.addAll(usersSet)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Toast.makeText(this@LogInActivity, "Failed to read", Toast.LENGTH_SHORT).show()
            }
        })
L */
        btnLogin.setOnClickListener {
            btnLogin.isEnabled = false
            btnLogin.text = "LOGGING IN"
            if (TextUtils.isEmpty(username.text.toString()) || TextUtils.isEmpty(password.text.toString())) {
                btnLogin.isEnabled = true
                btnLogin.text = "LOGIN"
                Toast.makeText(
                        this@LogInActivity,
                        "Username / Password Required",
                        Toast.LENGTH_LONG
                ).show()
            } else {

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

        buttonSkip.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }
        private fun login(username: String, password: String) {
            val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
            val logInInfo = LogInBody(username, password)
            retIn.login(logInInfo).enqueue(object : Callback<ResponseBody> {

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    btnLogin.isEnabled = true
                    btnLogin.text = "LOGIN"
                    Toast.makeText(
                            this@LogInActivity,
                            "Unknown error!",
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

/*
                        myRef.root.child("users").setValue(username)
L */
                        val editor = getSharedPreferences(PREFS, MODE_PRIVATE).edit()
                        editor.putString("username", username)
                        editor.apply()
                        var intent = Intent(this@LogInActivity, MenuActivity::class.java)
/*
                        intent.putExtra("users", usersList)
L */
                        startActivity(intent)

                    } else {
                        btnLogin.isEnabled = true
                        btnLogin.text = "LOGIN"
                        Toast.makeText(
                                this@LogInActivity,
                                "Username / Password invalid",
                                Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }

/*
    private fun updateUsers(dataSnapshot: DataSnapshot) {
        var key: String? = ""
        val i: Iterator<*> = dataSnapshot.children.iterator()

        while (i.hasNext()) {
            key = (i.next() as DataSnapshot).key
            if (!key.equals(username.text.toString(), ignoreCase = true)) {
                if (key != null) {
                    usersSet.add(key)
                }
            }
        }
    }
L */
}

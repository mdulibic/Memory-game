package hr.fer.ruzaosa.lecture4.ruzaosa.k.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.internal.api.FirebaseNoSignedInUserException
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import hr.fer.ruzaosa.lecture4.ruzaosa.R
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.RetrofitInstance
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.UsersService
import io.reactivex.internal.util.HalfSerializer.onComplete
import kotlinx.android.synthetic.main.activity_registration.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val username1 = findViewById<EditText>(R.id.usernameRegistration)
        val firstName1 = findViewById<EditText>(R.id.firstName)
        val lastName1 = findViewById<EditText>(R.id.lastName)
        val email1 = findViewById<EditText>(R.id.emailRegistration)
        val password1 = findViewById<EditText>(R.id.passwordRegistration)
        val btnRegister = findViewById<Button>(R.id.registerButton)

        btnRegister.setOnClickListener {
            btnRegister.isEnabled = false
            btnRegister.text = "REGISTERING"
            if (TextUtils.isEmpty(username1.text.toString()) || TextUtils.isEmpty(firstName1.text.toString()) ||
                    TextUtils.isEmpty(lastName1.text.toString()) || TextUtils.isEmpty(email1.text.toString()) ||
                    TextUtils.isEmpty(password1.text.toString())) {
                btnRegister.isEnabled = true
                btnRegister.text = "REGISTER"
                Toast.makeText(
                        this@RegistrationActivity,
                        "One or more empty fields!",
                        Toast.LENGTH_LONG
                ).show()
            } else {
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
                    signup(firstName, lastName, username, email, password, token)
                })
            }
        }
    }

    private fun signup(firstName: String, lastName: String, username: String,
                       email: String, password: String, token: String) {

        val btnRegister = findViewById<Button>(R.id.registerButton)
        val retIn = RetrofitInstance.getRetrofit().create(UsersService::class.java)
        val registerInfo = User(firstName, lastName, username, email, password, token)

        retIn.registerUser(registerInfo).enqueue(object :
                Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                btnRegister.isEnabled = true
                btnRegister.text = "REGISTER"
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
                    addUserToFirebase(username,token,email,password)
                    startActivity(Intent(this@RegistrationActivity, LogInActivity::class.java))
                } else {
                    btnRegister.isEnabled = true
                    btnRegister.text = "REGISTER"
                    Toast.makeText(this@RegistrationActivity, "Registration failed!", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        })
    }

    private fun addUserToFirebase(username:String,token:String,email:String,password:String) {
        var mDatabase:DatabaseReference
        var newUser:DatabaseReference
        var mCurrentUser:FirebaseUser
        mDatabase=FirebaseDatabase.getInstance().getReference().child("users")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    mCurrentUser = task.getResult()?.user!!
                    newUser=mDatabase.child(mCurrentUser.uid)
                    newUser.child("token").setValue(token)

                } else {


                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }

            }







    }
}
package hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit

import java.io.Serializable

data class User(val firstName: String,
                                val lastName: String,
                                val username: String,
                                val email: String,
                                val password: String,
                                val token: String,
                                var wins: Int) // dodala
    :Serializable{

}


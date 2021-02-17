package hr.fer.ruzaosa.projekt.ruzaosa.memory.data

import java.io.Serializable

data class User(val id: Long,val firstName: String,
                                val lastName: String,
                                val username: String,
                                val email: String,
                                val password: String,
                                val token: String,
                                var wins: Int) // dodala
    :Serializable{

}


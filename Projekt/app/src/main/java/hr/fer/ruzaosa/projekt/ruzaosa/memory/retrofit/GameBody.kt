package hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit

import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import java.io.Serializable

data class GameBody(val challenger: User, val challenged: User, val gameId: Long): Serializable {

}
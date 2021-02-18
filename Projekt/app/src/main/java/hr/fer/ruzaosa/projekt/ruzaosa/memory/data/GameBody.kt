package hr.fer.ruzaosa.projekt.ruzaosa.memory.data

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import java.io.Serializable
import java.time.LocalDateTime


data class GameBody(
     val challenger: User?, val challenged: User?
): Serializable {

                     }
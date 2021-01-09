package hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit

import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User

data class GameBody(val user1: User, val user2: User, val gameId: Long) {
    // je li potreban LocalDateTime1 / 2 ?
}
package hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit

import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.GameBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GameService {

    @Headers("Content-Type:application/json")
    @POST("createGame")
    fun createGame(
            @Body info: GameBody
    ): retrofit2.Call<Long>

    @Headers("Content-Type:application/json")
    @POST("sendNotifToChallenged")
    fun sendNotifToChallenged(
            @Body info: GameBody
    ): retrofit2.Call<ResponseBody>

    // return true if challenger did win, false otherwise
    @Headers("Content-Type:application/json")
    @POST("challengerFinished")
    fun challengerFinished(
            @Body gameId: Long
    ): retrofit2.Call<ResponseBody>

    // return true if challenged did win, false otherwise
    @Headers("Content-Type:application/json")
    @POST("challengedFinished")
    fun challengedFinished(
            @Body gameId: Long
    ): retrofit2.Call<ResponseBody>

}
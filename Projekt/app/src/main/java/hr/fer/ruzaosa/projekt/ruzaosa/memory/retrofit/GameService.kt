package hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit

import hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GameService {
    @Headers("Content-Type:application/json")
    @POST("gameAccepted")
    fun gameAccepted(
            @Body info: User
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("gameRejected")
    fun gameRejected(
            @Body info: User
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("createGame")
    fun createGame(
            @Body info: GameBody
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("challengerFinished")
    fun challengerFinished(
            @Body gameId: Long
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("challengedFinished")
    fun challengedFinished(
            @Body gameId: Long
    ): retrofit2.Call<ResponseBody>
}
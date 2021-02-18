package hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit

import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.LogInBody
import hr.fer.ruzaosa.projekt.ruzaosa.memory.data.User
import okhttp3.ResponseBody
import retrofit2.http.*

interface UsersService {

    @Headers("Content-Type:application/json")
    @POST("loginUser")
    fun login(@Body info: LogInBody): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("registerUser")
    fun registerUser(
        @Body info: User
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("updateToken")
    fun updateToken(
            @Body info: User
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @GET("getUsersList")
    fun getUsersList() : retrofit2.Call<List<User>>

    @Headers("Content-Type:application/json")
    @GET("gameAccepted/{username}")
    fun gameAccepted(
         @Path("username") username: String
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @GET("gameRejected/{username}")
    fun gameRejected(
        @Path("username") username: String
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @GET("gameCanceled/{username}")
    fun gameCanceled(
        @Path("username") username: String
    ): retrofit2.Call<ResponseBody>

    @Headers("Content-Type:application/json")
    @POST("getWins")
    fun getWins(
            @Body info: User
    ): retrofit2.Call<Long>

}

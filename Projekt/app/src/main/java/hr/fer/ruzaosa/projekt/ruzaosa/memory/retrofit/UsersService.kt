package hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit

import hr.fer.ruzaosa.projekt.ruzaosa.memory.retrofit.GameBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsersService {
    @Headers("Content-Type:application/json")//UVIK OVAJ HEADER ZA NOVI POST
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
    @POST("sendNotifToChallenged")
    fun sendNotifToChallenged(
            @Body info: User
    ): retrofit2.Call<ResponseBody>





}

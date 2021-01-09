package hr.fer.ruzaosa.lecture4.ruzaosa.k.retrofit

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

    // dodala
 // @Headers("Content-Type:application/json")
 // @POST("chooseWinner")
 // fun chooseWinner(@Body info: Game): retrofit2.Call<ResponseBody> // kako uključiti Game u FrontEnd?




}

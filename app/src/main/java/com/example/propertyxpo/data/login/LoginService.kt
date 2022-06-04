package com.example.propertyxpo.data.login

import com.example.propertyxpo.ui.login.model.LoginModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("_api/login_api.php")
    suspend fun login(@Body data: LoginModel): LoginModel

}
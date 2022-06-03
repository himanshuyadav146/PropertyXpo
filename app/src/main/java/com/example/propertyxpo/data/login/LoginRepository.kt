package com.example.propertyxpo.data.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.propertyxpo.data.Result
import com.example.propertyxpo.ui.login.model.LoginModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginService: LoginService) {

    fun doLogin(data:LoginModel) : LiveData<Result<Any>> {
        return flow {
            emit(Result.Loading)
            val loginResponse = loginService.login(data)
            emit(Result.Success(loginResponse))
        }.asLiveData(Dispatchers.IO)
    }
}
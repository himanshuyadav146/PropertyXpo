package com.example.propertyxpo.ui.login.viewmodel

import com.example.propertyxpo.ui.login.model.LoginModel
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.propertyxpo.base.BaseViewModel
import com.example.propertyxpo.common.AppController
import com.example.propertyxpo.data.Result
import com.example.propertyxpo.data.login.LoginRepository
import com.example.propertyxpo.ui.dashboard.DashBoardActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    var loginObject = MutableLiveData<LoginModel?>().apply {
        value = LoginModel("", -1, "", "", "", "")
    }
    @Inject lateinit var loginRepository :LoginRepository

    val apiState = MutableLiveData<Result<Any>>(Result.Success())

    fun onButtonClicked() {
        loginObject.value?.let { postLogin(it) }
    }

    fun checkForLogin(){
        if(loginRepository.isUserLoggedIn())
            navigateToDashboard()
    }

    private fun navigateToDashboard() {
        val intent =
            Intent(AppController.applicationContext(), DashBoardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        AppController.applicationContext().startActivity(intent)
    }


    private fun postLogin(objLogin: LoginModel) {

        loginRepository.doLogin(objLogin).observeForever {
            apiState.value = it
            when(it){
                is Result.Loading->{}
                is Result.Success->{
                    Toast.makeText(
                        AppController.applicationContext(),
                        "Login Succesfully",
                        Toast.LENGTH_LONG
                    ).show()

                   navigateToDashboard()
                }
                is Result.Failed ->{
                    Toast.makeText(AppController.applicationContext(), "Error", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}
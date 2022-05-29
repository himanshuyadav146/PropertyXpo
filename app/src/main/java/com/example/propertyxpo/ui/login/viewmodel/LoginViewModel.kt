package com.example.propertyxpo.ui.login.viewmodel

import com.example.propertyxpo.ui.login.model.LoginModel
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.propertyxpo.base.BaseViewModel
import com.example.propertyxpo.common.AppController
import com.example.propertyxpo.data.APIClient
import com.example.propertyxpo.data.login.LoginService
import com.example.propertyxpo.ui.DashBoardActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : BaseViewModel() {

    var loginObject = MutableLiveData<LoginModel?>().apply {
        value = LoginModel("", -1, "", "", "", "")
    }
    var loginService = APIClient().getClient().create(LoginService::class.java)

    fun onButtonClicked() {
        loginObject.value?.let { postLogin(it) }
    }


    private fun postLogin(objLogin: LoginModel) {

        val call = loginService.login(objLogin)
        call.enqueue(object : Callback<LoginModel> {
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                Toast.makeText(AppController.applicationContext(), "Error", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        AppController.applicationContext(),
                        "Login Succesfully",
                        Toast.LENGTH_LONG
                    ).show()

                    var intent =
                        Intent(AppController.applicationContext(), DashBoardActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    AppController.applicationContext().startActivity(intent)
                }
            }

        })

    }
}
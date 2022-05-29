package com.example.propertyxpo.ui.login.model

import com.google.gson.annotations.SerializedName


data class LoginModel (
	@SerializedName("message") var message : String,
	@SerializedName("userId") var userId : Int,
	@SerializedName("userName") var userName : String,
	@SerializedName("jwt") var jwt : String,
	@SerializedName("login_id") var login_id : String,
	@SerializedName("password") var password : String,
)
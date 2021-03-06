package com.example.propertyxpo.ui.login.view

import android.os.Bundle
import com.example.propertyxpo.R
import com.example.propertyxpo.base.BaseActivity
import com.example.propertyxpo.databinding.ActivityLoginBinding
import com.example.propertyxpo.ui.login.viewmodel.LoginViewModel

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_login)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel
        viewModel.checkForLogin()
    }
}
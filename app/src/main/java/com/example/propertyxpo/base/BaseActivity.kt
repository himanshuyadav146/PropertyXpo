package com.example.propertyxpo.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var dataBinding: B

    protected open fun bindView(layoutId: Int): B {
        if (!this::dataBinding.isInitialized)
            dataBinding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        setContentView(dataBinding.root)
        //dataBinding = DataBindingUtil.setContentView(this, layoutId);
//        setToolbar()
        return dataBinding
    }

}
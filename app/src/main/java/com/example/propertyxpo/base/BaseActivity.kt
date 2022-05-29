package com.example.propertyxpo.base

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

public abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    protected var dataBinding: B? = null

    protected open fun bindView(layoutId: Int): B {
        dataBinding = DataBindingUtil.inflate<B>(layoutInflater, layoutId, null, false)
        setContentView(dataBinding?.getRoot())
        //dataBinding = DataBindingUtil.setContentView(this, layoutId);
//        setToolbar()
        return dataBinding!!
    }

}
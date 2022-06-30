package com.example.propertyxpo.common.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import java.lang.StringBuilder

@BindingAdapter(value = ["bgTint", "bgAlpha"], requireAll = false)
fun View.bindBackgroundTint(color: String, alpha:String?){
    val stringBuilder = StringBuilder("#")
    if(alpha!=null)
        stringBuilder.append(alpha)
    stringBuilder.append(color.removePrefix("#"))
    try {
        backgroundTintList = ColorStateList.valueOf(Color.parseColor(stringBuilder.toString()))
    } catch (e: NumberFormatException) {
    }
}

@BindingAdapter(value=["isEnabled"])
fun View.isEnabled(isEnabled : Boolean){
    this.isEnabled = isEnabled
}
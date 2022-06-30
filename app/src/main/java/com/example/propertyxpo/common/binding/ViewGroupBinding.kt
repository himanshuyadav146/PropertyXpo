package com.example.propertyxpo.common.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.ViewGroup
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["bgTint", "bgAlpha"], requireAll = false)
fun ViewGroup.bindBackgroundTint(color: String, alpha:String?){
    val stringBuilder = StringBuilder("#")
    if(alpha!=null)
        stringBuilder.append(alpha)
    stringBuilder.append(color.removePrefix("#"))
    try {
        backgroundTintList = ColorStateList.valueOf(Color.parseColor(stringBuilder.toString()))
    } catch (e: NumberFormatException) {
    }
}
package com.example.propertyxpo.common.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("srcUrl")
fun ImageView.setImageUrl(url:String?){
//    if(!url.isNullOrBlank())
//        setImageResource(R.drawable.splash_logo)
//    else
//        Glide.with(this).load(url).error(R.drawable.splash_logo).into(this)
}

@BindingAdapter(value = ["bindTint"])
fun ImageView.bindTint(color:String){
    try {
        imageTintList = ColorStateList.valueOf(Color.parseColor(color))
    } catch (e: NumberFormatException) {
    }
}

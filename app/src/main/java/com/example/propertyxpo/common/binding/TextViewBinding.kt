package com.example.propertyxpo.common.binding

import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter(value = ["productSize", "productColor"], requireAll = false)
fun TextView.setSizeColorText(size:String? = "", color:String? = ""){
    size?.let { append(it) }
    if(!size.isNullOrBlank() && !color.isNullOrBlank()){
        append(" | ")
    }
    color?.let{append(it)}
}

@BindingAdapter(value = ["errorText"])
fun TextInputLayout.setErrorText(errorResorces: Int){
    error = if (errorResorces!=-1){
        context.getString(errorResorces)
    }else{
        null
    }

}

@BindingAdapter("htmlText")
fun TextView.htmlText(htmlText: String?) {
    text = htmlText?.let {
        HtmlCompat.fromHtml(
            it,
        HtmlCompat.FROM_HTML_MODE_COMPACT or HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST or HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM or HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_HEADING
                or HtmlCompat.FROM_HTML_OPTION_USE_CSS_COLORS
    )
    }
}
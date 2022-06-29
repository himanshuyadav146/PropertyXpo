package com.example.propertyxpo.common.extensions

import android.os.Build
import android.text.Html
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

/**
 * For listening for Layout changes.
 * Typical usage getting [View.getMeasuredHeight] or [View.getMeasuredWidth] of layout */
inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(isInvisible: Boolean = false) {
    visibility = if (isInvisible) View.INVISIBLE else View.GONE
}

fun MenuItem.show() {
    isVisible = true
}

fun MenuItem.hide() {
    isVisible = false
}

fun TextView.setFont(id: Int) {
    typeface = ResourcesCompat.getFont(context, id)
}
fun TextView.setHtmlText(text: String?, flags: Int = Html.FROM_HTML_MODE_LEGACY) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setText(Html.fromHtml(text, flags))
    } else
        setText(Html.fromHtml(text))
}

fun TextView.getTextLineCount(text: String, lineCount: (Int) -> (Unit)) {
    val params: PrecomputedTextCompat.Params = TextViewCompat.getTextMetricsParams(this)
    val ref: WeakReference<TextView>? = WeakReference(this)

    GlobalScope.launch(Dispatchers.Default) {
        val newText = PrecomputedTextCompat.create(text, params)
        GlobalScope.launch(Dispatchers.Main) {
            ref?.get()?.let { textView ->
                TextViewCompat.setPrecomputedText(textView, newText)
                lineCount.invoke(textView.lineCount)
            }
        }
    }
}
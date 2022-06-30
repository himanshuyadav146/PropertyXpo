package com.example.propertyxpo.common.extensions

import android.app.Activity
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.WindowInsets
import kotlin.math.roundToInt

fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

fun Activity.isKeyboardOpen(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        getRootView().rootWindowInsets?.isVisible(WindowInsets.Type.ime()) == true
    } else {
        val visibleBounds = Rect()
        this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
        val heightDiff = getRootView().height - visibleBounds.height()
        val marginOfError = this.convertDpToPx(50F).roundToInt()
        heightDiff > marginOfError
    }
}

fun Activity.isKeyboardClosed(): Boolean {
    return !this.isKeyboardOpen()
}
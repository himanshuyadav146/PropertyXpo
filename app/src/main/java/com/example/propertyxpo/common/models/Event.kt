package com.example.propertyxpo.common.models

import android.os.Bundle

sealed class Event {
    data class ClickEvent(val type: String, val bundle: Bundle?) : Event()
    data class AlertEvent(val type: String, val bundle: Bundle?) : Event()
    data class AnalyticsEvent(val type: String, val bundle: Bundle?) : Event()
    data class ToastEvent(val msg: String?) : Event()
    data class UiEvent(val type: String, val bundle: Bundle?) : Event()
}

package com.example.propertyxpo.ui.dashboard.models

import com.google.gson.annotations.SerializedName

data class MeetingRequestModel(
    @SerializedName("user_id") val userId: String,
    @SerializedName("planDate") val planDate: String
) {
}
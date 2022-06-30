package com.example.propertyxpo.ui.meeting.complete.models

import com.google.gson.annotations.SerializedName

data class MeetingCompleteRequest(
    @SerializedName("meeting_id") val meetingId:String,
    @SerializedName("meeting_status") val meetingStatus:String,
    @SerializedName("meeting_comment") val meetingComment:String,
    @SerializedName("user_id") val userId:String,
    @SerializedName("lead_id") val leadId:String,
)

package com.example.propertyxpo.ui.dashboard.models
import com.google.gson.annotations.SerializedName


data class MeetingResponse(val meetings: List<Meeting>)

data class Meeting(
    @SerializedName("agent")
    val agent: String?,
    @SerializedName("callId")
    val callId: String?,
    @SerializedName("cname")
    val cname: String?,
    @SerializedName("favorite")
    val favorite: String?,
    @SerializedName("leadId")
    val leadId: String?,
    @SerializedName("leadQuality")
    val leadQuality: String?,
    @SerializedName("metcomment")
    val meetComment: String?,
    @SerializedName("mobile")
    val mobile: String?,
    @SerializedName("nomasked")
    val nomAsked: String?,
    @SerializedName("pdate")
    val pDate: String?,
    @SerializedName("project")
    val project: String?
)
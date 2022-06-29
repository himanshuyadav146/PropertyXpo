package com.example.propertyxpo.ui.dashboard.models
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MeetingResponse(@SerializedName("list") val meetings: List<Meeting>) : Parcelable

@Parcelize
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
) : Parcelable
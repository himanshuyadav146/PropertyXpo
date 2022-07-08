package com.example.propertyxpo.data.meeting

import com.example.propertyxpo.ui.dashboard.models.Meeting
import com.example.propertyxpo.ui.dashboard.models.MeetingRequestModel
import com.example.propertyxpo.ui.dashboard.models.MeetingResponse
import com.example.propertyxpo.ui.meeting.complete.models.MeetingCompleteRequest
import com.google.gson.internal.LinkedTreeMap
import retrofit2.http.*

interface MeetingService {

    @POST("_api/meetingrem_plan.php")
    suspend fun getMeetingPlan(
        @Body meetingRequestModel: MeetingRequestModel,
        @Header("Authorization") bearerToken: String
    ): MeetingResponse

    @POST("_api/meetingrem_done.php")
    suspend fun getMeetingDone(
        @Body meetingRequestModel: MeetingRequestModel,
        @Header("Authorization") bearerToken: String
    ): MeetingResponse

    @POST("_api/callmeetingrem_plan.php")
    suspend fun getCallPlan(
        @Body meetingRequestModel: MeetingRequestModel,
        @Header("Authorization") bearerToken: String
    ): MeetingResponse

    @POST("_api/notmeetingrem_done.php")
    suspend fun getMeetingNotDone(
        @Body meetingRequestModel: MeetingRequestModel,
        @Header("Authorization") bearerToken: String
    ): MeetingResponse

    @POST("_api/callrem_done.php")
    suspend fun getCallDone(
        @Body meetingRequestModel: MeetingRequestModel,
        @Header("Authorization") bearerToken: String
    ): MeetingResponse

    @POST("_api/metstatus_update.php")
    suspend fun postMeetingStatus(
        @Body meetingCompleteRequest: MeetingCompleteRequest,
        @Header("Authorization") bearerToken: String):LinkedTreeMap<String,Any>
}
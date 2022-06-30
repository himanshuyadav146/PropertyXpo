package com.example.propertyxpo.data.meeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.propertyxpo.common.AppSharedPreferencesManager
import com.example.propertyxpo.data.Result
import com.example.propertyxpo.ui.dashboard.DashboardViewModel
import com.example.propertyxpo.ui.dashboard.models.Meeting
import com.example.propertyxpo.ui.dashboard.models.MeetingRequestModel
import com.example.propertyxpo.ui.meeting.complete.models.MeetingCompleteRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class MeetingRepository @Inject constructor(
    private val meetingService: MeetingService,
    private val appSharedPreferencesManager: AppSharedPreferencesManager
) {
    @Inject
    @Named("Token")
    lateinit var bearerToken: String

    val userId = "1"

    fun fetchMeetingPlan(type: String): LiveData<Result<Any>> {
        return flow {
            emit(Result.Loading)
            val meetingResponse = when (type) {
                DashboardViewModel.MEET_PLAN -> meetingService.getMeetingPlan(
                    MeetingRequestModel(userId),
                    bearerToken
                )
                DashboardViewModel.CALL_PLAN -> meetingService.getCallPlan(
                    MeetingRequestModel(userId),
                    bearerToken
                )
                DashboardViewModel.MEET_DONE -> meetingService.getMeetingDone(
                    MeetingRequestModel(userId),
                    bearerToken
                )
                DashboardViewModel.MEET_NOT_DONE -> meetingService.getMeetingNotDone(
                    MeetingRequestModel(userId),
                    bearerToken
                )
                DashboardViewModel.CALL_DONE -> meetingService.getCallDone(
                    MeetingRequestModel(userId),
                    bearerToken
                )
                else -> meetingService.getMeetingPlan(MeetingRequestModel(userId), bearerToken)

            }
            emit(Result.Success(meetingResponse))
        }.asLiveData(Dispatchers.IO)
    }

    fun updateMeeting(meeting: Meeting, status: String, comments: String): LiveData<Result<Any>> {
        return flow {
            emit(Result.Loading)
            meeting.run {
                val updateResponse = meetingService.postMeetingStatus(
                    MeetingCompleteRequest(
                        callId.toString(),
                        status,
                        comments,
                        userId,
                        leadId.toString()
                    ), bearerToken
                )
                emit(Result.Success(updateResponse))
            }
        }.asLiveData(Dispatchers.IO)
    }

    fun doLogout() {
        appSharedPreferencesManager.removePreferencesForLogout()
    }

}
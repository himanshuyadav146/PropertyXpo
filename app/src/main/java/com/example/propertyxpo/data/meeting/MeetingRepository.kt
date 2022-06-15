package com.example.propertyxpo.data.meeting

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.propertyxpo.common.AppSharedPreferencesManager
import com.example.propertyxpo.data.Result
import com.example.propertyxpo.ui.dashboard.DashboardViewModel
import com.example.propertyxpo.ui.dashboard.models.MeetingRequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

class MeetingRepository @Inject constructor(private val meetingService: MeetingService, private val appSharedPreferencesManager: AppSharedPreferencesManager) {
    @Inject
    @Named("Token")
    lateinit var bearerToken: String

    fun fetchMeetingPlan(type: String): LiveData<Result<Any>> {
        return flow {
            emit(Result.Loading)
            val meetingResponse = when (type) {
                DashboardViewModel.MEET_PLAN -> meetingService.getMeetingPlan(
                    MeetingRequestModel("1"),
                    bearerToken
                )
                DashboardViewModel.CALL_PLAN -> meetingService.getCallPlan(
                    MeetingRequestModel("1"),
                    bearerToken
                )
                DashboardViewModel.MEET_DONE -> meetingService.getMeetingDone(
                    MeetingRequestModel("1"),
                    bearerToken
                )
                DashboardViewModel.MEET_NOT_DONE -> meetingService.getMeetingNotDone(
                    MeetingRequestModel("1"),
                    bearerToken
                )
                DashboardViewModel.CALL_DONE -> meetingService.getCallDone(
                    MeetingRequestModel("1"),
                    bearerToken
                )
                else -> meetingService.getMeetingPlan(MeetingRequestModel("1"), bearerToken)

            }
            emit(Result.Success(meetingResponse))
        }.asLiveData(Dispatchers.IO)
    }

    fun doLogout(){
        appSharedPreferencesManager.removePreferencesForLogout()
    }

}
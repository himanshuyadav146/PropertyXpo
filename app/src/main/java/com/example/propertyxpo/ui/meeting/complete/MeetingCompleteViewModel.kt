package com.example.propertyxpo.ui.meeting.complete

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.propertyxpo.base.BaseViewModel
import com.example.propertyxpo.common.StringConstants
import com.example.propertyxpo.common.models.Event
import com.example.propertyxpo.data.Result
import com.example.propertyxpo.data.meeting.MeetingRepository
import com.example.propertyxpo.ui.dashboard.models.Meeting
import com.example.propertyxpo.ui.dashboard.models.MeetingResponse
import com.google.gson.internal.LinkedTreeMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MeetingCompleteViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var meetingRepository: MeetingRepository

    var meeting: Meeting? = null
    var status: String? = null
    val comments = MutableLiveData("")

    fun setData(args: Bundle) {
        meeting = args.getParcelable(StringConstants.MEETING_DATA)
        status = args.getString(StringConstants.MEETING_STATUS)
    }

    fun onSubmit() {
        meeting?.let { status?.let { it1 ->
            meetingRepository.updateMeeting(it,
                it1, comments.value ?: "").observeForever {
                when(it){
                    is Result.Loading->{}
                    is Result.Success->{
                        val updateResponse = it.data as LinkedTreeMap<String, Any>
                        if((updateResponse["list"] as LinkedTreeMap<String, String>)["message"].equals("successful", true)){
                            singleEventLiveData.value = Event.UiEvent("success",null)
                        }
                    }
                    is Result.Failed ->{

                    }
                }
            }
        } }
    }

}
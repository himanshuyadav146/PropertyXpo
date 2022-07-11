package com.example.propertyxpo.ui.dashboard

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.propertyxpo.R
import com.example.propertyxpo.base.BaseViewModel
import com.example.propertyxpo.common.AppController
import com.example.propertyxpo.common.StringConstants
import com.example.propertyxpo.common.models.Event
import com.example.propertyxpo.data.Result
import com.example.propertyxpo.data.meeting.MeetingRepository
import com.example.propertyxpo.ui.dashboard.adapters.MeetingAdapter
import com.example.propertyxpo.ui.dashboard.models.Meeting
import com.example.propertyxpo.ui.dashboard.models.MeetingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var meetingRepository: MeetingRepository

    private val _selectedDateLong: MutableLiveData<Long> =
        MutableLiveData(System.currentTimeMillis())
    private var selectedMeetingType = MEET_PLAN

    val selectedDateLong :LiveData<Long> = _selectedDateLong


    val meetingAdapter = MeetingAdapter(this)

    val searchHint = MutableLiveData(
        AppController.applicationContext().getString(R.string.hint_dashboard_search_crm_id)
    )

    fun setSelectedDate(selectedDateLong: Long) {
        this._selectedDateLong.value = selectedDateLong
        fetchData()
    }

    fun fetchData() {
        getMeetingPlan(selectedMeetingType)
    }


    val apiState = MutableLiveData<Result<Any>>(Result.Success())
    private fun getMeetingPlan(type: String) {

        val selectedDateString = _selectedDateLong.value?.let {
            Date(
                it
            )
        }?.let { SimpleDateFormat("yyyy-MM-dd").format(it) } ?: ""

        meetingRepository.fetchMeetingPlan(type, selectedDateString).observeForever {
            apiState.value = it
            when (it) {
                is Result.Loading -> {}
                is Result.Success -> {
                    val meetingResponse = it.data as MeetingResponse
                    meetingAdapter.updateMeetings((meetingResponse.meetings))
                }
                is Result.Failed -> {

                }
            }
        }

    }

    fun onMeetingTypeChanged(checkedId: Int?) {
        selectedMeetingType = when (checkedId) {
            R.id.chipCallPlan -> CALL_PLAN
            R.id.chipMeetPlan -> MEET_PLAN
            R.id.chipMeetDone -> MEET_DONE
            R.id.chipNotMet -> MEET_NOT_DONE
            R.id.chipCallHeld -> CALL_DONE
            else -> selectedMeetingType
        }
        fetchData()
    }

    fun onSearchTypeChanged(checkedId: Int) {
        searchHint.value = if (checkedId == R.id.rdbCid)
            AppController.applicationContext().getString(R.string.hint_dashboard_search_crm_id)
        else
            AppController.applicationContext().getString(R.string.hint_dashboard_search_name)
    }

    fun onClick(type: String, meeting: Meeting? = null) {
        when (type) {
            STATUS -> {
                singleEventLiveData.value = Event.ClickEvent(type, Bundle().apply {
                    putParcelable(StringConstants.MEETING_DATA, meeting)
                })
            }
            CALENDAR -> {
                singleEventLiveData.value = Event.UiEvent(CALENDAR, Bundle().apply {
                    _selectedDateLong.value?.let { putLong(DATA_PARAM_LABEL, it) }
                })
            }
        }


    }

    fun doLogout() {
        meetingRepository.doLogout()
    }

    companion object {
        const val MEET_PLAN = "meet_plan"
        const val CALL_PLAN = "call_plan"
        const val MEET_DONE = "meet_done"
        const val MEET_NOT_DONE = "meet_not_done"
        const val CALL_DONE = "call_done"

        const val DATA_PARAM_LABEL = "_data"

        /**
         * CTA types*/
        const val STATUS = "cta_status"
        const val CALENDAR = "cta_calendar"
    }
}
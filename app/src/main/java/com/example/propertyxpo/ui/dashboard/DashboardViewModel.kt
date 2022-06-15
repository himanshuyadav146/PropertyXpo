package com.example.propertyxpo.ui.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.propertyxpo.R
import com.example.propertyxpo.common.AppController
import com.example.propertyxpo.data.Result
import com.example.propertyxpo.data.meeting.MeetingRepository
import com.example.propertyxpo.ui.dashboard.adapters.MeetingAdapter
import com.example.propertyxpo.ui.dashboard.models.Meeting
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {
    @Inject lateinit var meetingRepository:MeetingRepository

    val meetingAdapter = MeetingAdapter()

    val searchHint = MutableLiveData(AppController.applicationContext().getString(R.string.hint_dashboard_search_crm_id))

    fun fetchData(){
        getMeetingPlan(MEET_PLAN)
    }


    val apiState = MutableLiveData<Result<Any>>(Result.Success())
    private fun getMeetingPlan(type:String){

            meetingRepository.fetchMeetingPlan(type).observeForever {
                apiState.value = it
                when(it){
                    is Result.Loading->{}
                    is Result.Success->{
                        meetingAdapter.updateMeetings((it.data as List<Meeting>))
                    }
                    is Result.Failed ->{

                    }
                }
            }

    }

    fun onMeetingTypeChanged(checkedId:Int?){
        getMeetingPlan(when(checkedId){
            R.id.chipCallPlan -> CALL_PLAN
            R.id.chipMeetPlan -> MEET_PLAN
            R.id.chipMeetDone -> MEET_DONE
            R.id.chipNotMet -> MEET_NOT_DONE
            R.id.chipCallHeld -> CALL_DONE
            else -> MEET_PLAN
        })
    }

    fun onSearchTypeChanged(checkedId: Int){
        searchHint.value = if(checkedId == R.id.rdbCid)
            AppController.applicationContext().getString(R.string.hint_dashboard_search_crm_id)
        else
            AppController.applicationContext().getString(R.string.hint_dashboard_search_name)
    }

    fun doLogout(){
        meetingRepository.doLogout()
    }

    companion object{
        const val MEET_PLAN = "meet_plan"
        const val CALL_PLAN = "call_plan"
        const val MEET_DONE = "meet_done"
        const val MEET_NOT_DONE = "meet_not_done"
        const val CALL_DONE = "call_done"
    }
}
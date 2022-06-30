package com.example.propertyxpo.ui.common

import android.os.Bundle
import com.example.propertyxpo.R
import com.example.propertyxpo.base.BaseActivity
import com.example.propertyxpo.common.StringConstants
import com.example.propertyxpo.databinding.ActivityDialogFragmentContainerBinding
import com.example.propertyxpo.ui.meeting.MeetingStatusUpdateDialogFragment
import com.example.propertyxpo.ui.meeting.add.AddMeetingFragment

class DialogFragmentContainerActivity : BaseActivity<ActivityDialogFragmentContainerBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_dialog_fragment_container)
        intent?.getStringExtra(StringConstants.SCREEN_TYPE)?.let {
            openFragmentByType(it)
        } ?: let {
            finish()
            return
        }
    }

    private fun openFragmentByType(screenType: String) {
        val fragment = when(screenType){
            StringConstants.ADD_MEETING -> AddMeetingFragment()
            StringConstants.MEETING_COMPLETE -> MeetingStatusUpdateDialogFragment()
            else-> AddMeetingFragment()
        }
        supportFragmentManager.beginTransaction().add(fragment, "home")
            .commit()
    }
}
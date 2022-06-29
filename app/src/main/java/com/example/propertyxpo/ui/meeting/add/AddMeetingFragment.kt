package com.example.propertyxpo.ui.meeting.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.propertyxpo.R

class AddMeetingFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_meeting, container, false)?:super.onCreateView(inflater, container, savedInstanceState)
    }
}
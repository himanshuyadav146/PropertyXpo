package com.example.propertyxpo.ui.meeting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.propertyxpo.R
import com.example.propertyxpo.common.StringConstants
import com.example.propertyxpo.databinding.FragmentMeetingStatusUpdateBinding
import com.example.propertyxpo.ui.dashboard.models.Meeting
import com.example.propertyxpo.ui.meeting.complete.MeetingCompleteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeetingStatusUpdateDialogFragment : DialogFragment() {

    lateinit var binding: FragmentMeetingStatusUpdateBinding

    companion object{
        fun instance(meeting: Meeting, status:String): MeetingStatusUpdateDialogFragment {
            return MeetingStatusUpdateDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(StringConstants.MEETING_DATA, meeting)
                    putString(StringConstants.MEETING_STATUS, status)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager.setFragmentResultListener("update", this){ key, bundle ->
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeetingStatusUpdateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, MeetingCompleteFragment::class.java, arguments)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
        }
    }
}
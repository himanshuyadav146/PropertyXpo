package com.example.propertyxpo.ui.meeting.complete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.propertyxpo.R
import com.example.propertyxpo.common.StringConstants
import com.example.propertyxpo.common.models.Event
import com.example.propertyxpo.databinding.FragmentMeetingCompleteBinding
import com.example.propertyxpo.ui.dashboard.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeetingCompleteFragment: Fragment() {

    lateinit var binding : FragmentMeetingCompleteBinding
    private val viewModel by viewModels<MeetingCompleteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeetingCompleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        arguments?.let { viewModel.setData(it) }
        binding.meeting = arguments?.getParcelable(StringConstants.MEETING_DATA)
        binding.viewModel = viewModel

        viewModel.singleEventLiveData.observe(viewLifecycleOwner){
            when(it){
                is Event.UiEvent -> {
                    when(it.type){
                        "success" ->{
                            setFragmentResult("update",Bundle())
                        }
                    }
                }
                else -> {}
            }
        }
    }
}
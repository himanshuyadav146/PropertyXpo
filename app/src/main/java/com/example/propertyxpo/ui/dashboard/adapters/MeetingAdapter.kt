package com.example.propertyxpo.ui.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.propertyxpo.databinding.ItemMeetingBinding
import com.example.propertyxpo.ui.dashboard.DashboardViewModel
import com.example.propertyxpo.ui.dashboard.models.Meeting

class MeetingAdapter(val viewModel: DashboardViewModel):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val meetings = ArrayList<Meeting>()

    fun updateMeetings(updatedMeetings:List<Meeting>){
        meetings.clear()
        meetings.addAll(updatedMeetings)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemMeetingViewHolder(ItemMeetingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemMeetingViewHolder).bind(meetings[position], viewModel)
    }

    override fun getItemCount(): Int {
        return meetings.size
    }

    class ItemMeetingViewHolder(private val binding : ItemMeetingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(meeting: Meeting, viewModel: DashboardViewModel){
            binding.meeting = meeting
            binding.viewModel = viewModel
        }
    }
}
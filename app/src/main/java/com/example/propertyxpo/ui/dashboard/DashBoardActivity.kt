package com.example.propertyxpo.ui.dashboard

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.propertyxpo.R
import com.example.propertyxpo.base.BaseActivity
import com.example.propertyxpo.common.StringConstants
import com.example.propertyxpo.common.models.Event
import com.example.propertyxpo.databinding.ActivityDashBoardBinding
import com.example.propertyxpo.ui.dashboard.models.Meeting
import com.example.propertyxpo.ui.login.view.LoginActivity
import com.example.propertyxpo.ui.meeting.MeetingStatusUpdateDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : BaseActivity<ActivityDashBoardBinding>() {

    private val viewModel by viewModels<DashboardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView(R.layout.activity_dash_board)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel

        setSupportActionBar(dataBinding.toolbar)

        addClickListeners()
        addObservers()

        initDashboard()
    }

    private fun addClickListeners() {
        dataBinding.fabAdd.setOnClickListener {
            dataBinding.fabAdd.isExpanded = !dataBinding.fabAdd.isExpanded
        }

        dataBinding.chpGrpMeetCategory.setOnCheckedStateChangeListener { _, checkedIds ->
            viewModel.onMeetingTypeChanged(checkedIds.firstOrNull())
        }

        dataBinding.fabAdd.setOnClickListener {
            Toast.makeText(this, "Add Meet/Call Plan", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addObservers() {
        viewModel.singleEventLiveData.observe(this) {
            when (it) {
                is Event.ClickEvent -> {
                    when (it.type) {
                        DashboardViewModel.STATUS -> {

                            AlertDialog.Builder(this)
                                .setItems(R.array.array_meeting_status_choices) { _, i ->
                                    openMeetingCompleteScreen(
                                        it.bundle?.getParcelable(StringConstants.MEETING_DATA),
                                        when (i) {
                                            0 -> "held"
                                            1 -> "not held"
                                            else -> "confirmed"
                                        }
                                    )
                                }.show()
                        }
                    }
                }
                is Event.UiEvent -> {
                    when(it.type){
                        DashboardViewModel.CALENDAR -> {
                            val selectedDateLong = it.bundle?.getLong(DashboardViewModel.DATA_PARAM_LABEL)
                            val datePicker = MaterialDatePicker.Builder.datePicker()
                                .setSelection(selectedDateLong)
                                .build()

                            datePicker.addOnPositiveButtonClickListener { selectedDate ->
                                viewModel.setSelectedDate(selectedDate)
                            }
                            datePicker.show(supportFragmentManager, "calendar")
                        }
                    }
                }
                else -> {}
            }
        }
    }

    private fun initDashboard() {
        viewModel.fetchData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        AlertDialog.Builder(this).setTitle("Logout")
            .setMessage("Do you want to logout?")
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                viewModel.doLogout()
                openLoginScreen()
            }
            .setNegativeButton("No", null)
            .show()
        return super.onOptionsItemSelected(item)
    }

    private fun openLoginScreen() {
        startActivity(
            Intent(this, LoginActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    private fun openMeetingCompleteScreen(meeting: Meeting?, status: String) {
        meeting?.let {
            MeetingStatusUpdateDialogFragment.instance(it, status)
                .show(supportFragmentManager, "meetingComplete")
        }
    }
}
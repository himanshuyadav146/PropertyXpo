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
import com.example.propertyxpo.databinding.ActivityDashBoardBinding
import com.example.propertyxpo.ui.login.view.LoginActivity
import com.google.android.material.card.MaterialCardView
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

        dataBinding.fabAdd.setOnClickListener {
            dataBinding.fabAdd.isExpanded = !dataBinding.fabAdd.isExpanded
        }

        dataBinding.chpGrpMeetCategory.setOnCheckedStateChangeListener { group, checkedIds ->
            viewModel.onMeetingTypeChanged(checkedIds.firstOrNull())
        }
        
        dataBinding.fabAdd.setOnClickListener {
            Toast.makeText(this, "Add Meet/Call Plan", Toast.LENGTH_SHORT).show()
        }

        initDashboard()

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
            .setPositiveButton("Yes"){ _: DialogInterface, _: Int ->
                viewModel.doLogout()
                openLoginScreen()
            }
            .setNegativeButton("No",null)
            .show()
        return super.onOptionsItemSelected(item)
    }

    private fun openLoginScreen() {
        startActivity(Intent(this,LoginActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}
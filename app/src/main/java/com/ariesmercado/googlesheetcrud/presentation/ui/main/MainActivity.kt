package com.ariesmercado.googlesheetcrud.presentation.ui.main

import android.app.ProgressDialog
import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.ariesmercado.googlesheetcrud.R
import com.ariesmercado.googlesheetcrud.common.base.BaseActivity
import com.ariesmercado.googlesheetcrud.common.util.collectLatestData
import com.ariesmercado.googlesheetcrud.common.util.showToastShort
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Employee
import com.ariesmercado.googlesheetcrud.databinding.ActivityMainBinding
import com.ariesmercado.googlesheetcrud.presentation.ui.manage.ManageEmployeeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel,ActivityMainBinding>(), EmployeeAdapter.EmployeeAdapterListener {
    override val layoutId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private lateinit var employeeAdapter: EmployeeAdapter
    private lateinit var progressDialog: ProgressDialog

    override fun initViews() {
        super.initViews()
        initEmployeeAdapter()

        progressDialog = ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        binding.buttonAdd.setOnClickListener {
            loadManageEmployeeScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGoogleSheetData()
    }

    override fun subscribe() {
        super.subscribe()

        viewModel.employeeState.collectLatestData(lifecycleScope) {
            if(it.isLoading) {
                if (!progressDialog.isShowing) progressDialog.show()
            }

            it.data?.records?.let { employees ->
                employeeAdapter.updateData(employees)
                progressDialog.dismiss()
            }

        }

        viewModel.response.collectLatestData(lifecycleScope) {
            if(it.isLoading) {
                progressDialog.show()
            }
            it.data?.let { data ->
                viewModel.getGoogleSheetData()
                this.showToastShort(data.message ?: "")
            }
        }
    }

    private fun initEmployeeAdapter() {
        employeeAdapter = EmployeeAdapter(emptyList(), this)
        binding.recyclerviewEmployees.adapter = employeeAdapter
    }

    private fun loadManageEmployeeScreen(employee: Employee? = null) {
        val intent = Intent(this, ManageEmployeeActivity::class.java)
        employee?.let {
            intent.putExtra("USERNAME", employee.username)
            intent.putExtra("EMAIL", employee.email)
        }
        startActivity(intent)
    }

    override fun onDeleteClickListener(id: Int) {
        this.showToastShort("Deleting...")
        viewModel.deleteData(id)
    }
}
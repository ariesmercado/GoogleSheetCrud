package com.ariesmercado.googlesheetcrud.presentation.ui.manage

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.ariesmercado.googlesheetcrud.R
import com.ariesmercado.googlesheetcrud.common.base.BaseActivity
import com.ariesmercado.googlesheetcrud.common.util.collectLatestData
import com.ariesmercado.googlesheetcrud.common.util.showToastShort
import com.ariesmercado.googlesheetcrud.databinding.ActivityManageEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageEmployeeActivity : BaseActivity<ManageEmployeeViewModel, ActivityManageEmployeeBinding>() {
    override val layoutId: Int = R.layout.activity_manage_employee
    override val viewModel: ManageEmployeeViewModel by viewModels()

    private lateinit var progressDialog: ProgressDialog

    override fun initViews() {
        super.initViews()

        progressDialog = ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        binding.btnSubmit.setOnClickListener {
            if(binding.edtUsername.text.toString().isNotBlank() && binding.edtEmail.text.toString().isNotBlank()) {
                viewModel.sendData(binding.edtUsername.text.toString() , binding.edtEmail.text.toString())
                this.showToastShort("Sending...")
            } else {
                this.showToastShort("Incomplete forms")
            }
        }
    }

    override fun subscribe() {
        super.subscribe()

        viewModel.response.collectLatestData(lifecycleScope) {
            if(it.isLoading) {
                progressDialog.show()
            }

            it.data?.let {data ->
                progressDialog.dismiss()
                this.showToastShort(data.result ?: "")
                finish()
            }
        }
    }

}
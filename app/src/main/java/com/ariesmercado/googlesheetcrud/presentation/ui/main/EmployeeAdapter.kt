package com.ariesmercado.googlesheetcrud.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ariesmercado.googlesheetcrud.BR
import com.ariesmercado.googlesheetcrud.R
import com.ariesmercado.googlesheetcrud.data.source.remote.dto.Employee
import com.ariesmercado.googlesheetcrud.databinding.ItemEmployeeBinding

class EmployeeAdapter(private var data: List<Employee>, private var listener: EmployeeAdapterListener) :
    RecyclerView.Adapter<EmployeeAdapter.AdapterViewHolder>() {

    interface EmployeeAdapterListener {
        fun onDeleteClickListener(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val binding: ItemEmployeeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_employee,
            parent,
            false
        )
        return AdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    fun updateData(data: List<Employee>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class AdapterViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Employee) {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()

            binding.buttonDelete.setOnClickListener {
                this@EmployeeAdapter.listener.onDeleteClickListener(item.id)
            }
        }
    }
}
package com.massive.smarthome.ui.component.home.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.databinding.HeaterItemBinding
import com.massive.smarthome.ui.base.listeners.RecyclerItemListener

class HeaterViewHolder(private val itemBinding: HeaterItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(deviceItem: HeaterDevice, recyclerItemListener: RecyclerItemListener<HeaterDevice>) {
        itemBinding.tvDeviceName.text = deviceItem.deviceName
        itemBinding.swMode.isChecked = deviceItem.mode.toBoolean()
        itemBinding.sbTemp.progress = deviceItem.temperature ?: 0
        itemBinding.rlHeaterItem.setOnClickListener { recyclerItemListener.onItemSelected(deviceItem) }
    }
}
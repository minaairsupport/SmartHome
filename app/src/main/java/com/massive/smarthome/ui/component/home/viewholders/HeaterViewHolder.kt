package com.massive.smarthome.ui.component.home.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.databinding.HeaterItemBinding
import com.massive.smarthome.ui.base.listeners.RecyclerItemListener
import com.massive.smarthome.utils.ON

class HeaterViewHolder(private val itemBinding: HeaterItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(deviceItem: HeaterDevice, recyclerItemListener: RecyclerItemListener<Device>) {
        itemBinding.tvDeviceName.text = deviceItem.deviceName
        itemBinding.swMode.isChecked = deviceItem.mode == ON
        itemBinding.tvTemp.text = "Temp ${deviceItem.temperature}"
        itemBinding.rlHeaterItem.setOnClickListener { recyclerItemListener.onItemSelected(deviceItem) }
    }
}
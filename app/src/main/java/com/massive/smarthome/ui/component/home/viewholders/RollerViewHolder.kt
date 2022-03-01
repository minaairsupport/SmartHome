package com.massive.smarthome.ui.component.home.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.RollerDevice
import com.massive.smarthome.databinding.RollerItemBinding
import com.massive.smarthome.ui.base.listeners.RecyclerItemListener

class RollerViewHolder(private val itemBinding: RollerItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(deviceItem: RollerDevice, recyclerItemListener: RecyclerItemListener<Device>) {
        itemBinding.tvDeviceName.text = deviceItem.deviceName
        itemBinding.sbPosition.progress = deviceItem.position ?: 0
        itemBinding.rlRollerItem.setOnClickListener { recyclerItemListener.onItemSelected(deviceItem) }
    }
}
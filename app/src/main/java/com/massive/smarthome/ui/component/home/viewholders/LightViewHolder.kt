package com.massive.smarthome.ui.component.home.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.databinding.LightItemBinding
import com.massive.smarthome.ui.base.listeners.RecyclerItemListener
import com.massive.smarthome.utils.ON

class LightViewHolder(private val itemBinding: LightItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(deviceItem: LightDevice, recyclerItemListener: RecyclerItemListener<Device>) {
        itemBinding.tvDeviceName.text = deviceItem.deviceName
        itemBinding.swMode.isChecked = deviceItem.mode == ON
        itemBinding.sbIntensity.text = "Intensity ${deviceItem.intensity}"
        itemBinding.rlLightItem.setOnClickListener { recyclerItemListener.onItemSelected(deviceItem) }
    }
}
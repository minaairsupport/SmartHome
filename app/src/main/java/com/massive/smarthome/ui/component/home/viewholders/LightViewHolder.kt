package com.massive.smarthome.ui.component.home.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.databinding.LightItemBinding
import com.massive.smarthome.ui.base.listeners.RecyclerItemListener

class LightViewHolder(private val itemBinding: LightItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(deviceItem: LightDevice, recyclerItemListener: RecyclerItemListener<LightDevice>) {
        itemBinding.tvDeviceName.text = deviceItem.deviceName
        itemBinding.swMode.isChecked = deviceItem.mode.toBoolean()
        itemBinding.sbIntensity.progress = deviceItem.intensity ?: 0
        itemBinding.rlLightItem.setOnClickListener { recyclerItemListener.onItemSelected(deviceItem) }
    }
}
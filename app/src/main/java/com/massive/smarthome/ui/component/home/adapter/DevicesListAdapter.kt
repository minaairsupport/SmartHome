package com.massive.smarthome.ui.component.home.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import com.massive.smarthome.databinding.HeaterItemBinding
import com.massive.smarthome.databinding.LightItemBinding
import com.massive.smarthome.databinding.RollerItemBinding
import com.massive.smarthome.ui.base.listeners.RecyclerItemListener
import com.massive.smarthome.ui.component.home.DevicesListViewModel
import com.massive.smarthome.ui.component.home.viewholders.HeaterViewHolder
import com.massive.smarthome.ui.component.home.viewholders.LightViewHolder
import com.massive.smarthome.ui.component.home.viewholders.RollerViewHolder
import com.massive.smarthome.ui.component.profile.ProfileActivity
import com.massive.smarthome.utils.ALL_TYPE
import com.massive.smarthome.utils.DevicesTypes.*
import com.massive.smarthome.utils.HEATER_TYPE
import com.massive.smarthome.utils.LIGHT_TYPE
import com.massive.smarthome.utils.ROLLER_TYPE

class DevicesListAdapter(private val viewModel: DevicesListViewModel, private val devices: ArrayList<Device>, private val onItemClickListener: RecyclerItemListener<Device>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var filteredDevices :ArrayList<Device> =  ArrayList(devices)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var myViewHolder: RecyclerView.ViewHolder? = null
        when(viewType){
            LIGHT.ordinal -> {
                val itemBinding = LightItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                myViewHolder =  LightViewHolder(itemBinding)

            }
            HEATER.ordinal ->  {
                val itemBinding = HeaterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                myViewHolder =  HeaterViewHolder(itemBinding)
            }
            ROLLER.ordinal ->  {
                val itemBinding = RollerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                myViewHolder = RollerViewHolder(itemBinding)
            }

        }
        return myViewHolder!!
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is LightViewHolder -> {
                holder.bind(filteredDevices[position] as LightDevice , onItemClickListener)
            }
            is HeaterViewHolder ->  {
                holder.bind(filteredDevices[position] as HeaterDevice, onItemClickListener)
            }
            is RollerViewHolder ->  {
                holder.bind(filteredDevices[position] as RollerDevice , onItemClickListener)
            }
        }

    }

    override fun getItemCount(): Int {
        return filteredDevices.size
    }

    fun filterByType(deviceType: String){
        filteredDevices.clear()
        when(deviceType){
            LIGHT_TYPE -> filteredDevices.addAll(devices.filterIsInstance(LightDevice::class.java))
            ROLLER_TYPE -> filteredDevices.addAll(devices.filterIsInstance(RollerDevice::class.java))
            HEATER_TYPE -> filteredDevices.addAll(devices.filterIsInstance(HeaterDevice::class.java))
            ALL_TYPE -> filteredDevices.addAll(devices)

        }

        notifyDataSetChanged()

    }

    override fun getItemViewType(position: Int): Int {
        return when (filteredDevices[position]){
            is LightDevice -> LIGHT.ordinal
            is HeaterDevice -> HEATER.ordinal
            is RollerDevice -> ROLLER.ordinal
        }
    }
}
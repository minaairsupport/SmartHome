package com.massive.smarthome.ui.component.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
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
import com.massive.smarthome.utils.DevicesTypes.*

class DevicesListAdapter(private val viewModel: DevicesListViewModel, private val devices: List<Device>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val onItemClickListener: RecyclerItemListener<Device> = object : RecyclerItemListener<Device> {
        override fun onItemSelected(item: Device) {
            // TODO handle item click
        }
    }

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
                holder.bind(devices[position] as LightDevice , onItemClickListener)
            }
            is HeaterViewHolder ->  {
                holder.bind(devices[position] as HeaterDevice, onItemClickListener)
            }
            is RollerViewHolder ->  {
                holder.bind(devices[position] as RollerDevice , onItemClickListener)
            }
        }

    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (devices[position]){
            is LightDevice -> LIGHT.ordinal
            is HeaterDevice -> HEATER.ordinal
            is RollerDevice -> ROLLER.ordinal
        }
    }
}
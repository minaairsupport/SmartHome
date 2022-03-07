package com.massive.smarthome.ui.component.detailsDevice.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.databinding.LightDetailsBinding
import com.massive.smarthome.utils.DeviceKey
import com.massive.smarthome.utils.ON
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LightDetailsFragment : Fragment() {
    private lateinit var binding: LightDetailsBinding
    private val viewModel: LightDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LightDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       var device = arguments?.getParcelable<LightDevice>(DeviceKey)!!

        initView(device)
    }
    private fun initView(device: LightDevice){
        binding.tvDeviceName.text = device.deviceName
        binding.sbIntensity.progress = device.intensity
        binding.swMode.isChecked = device.mode == ON
        binding.btDelete.setOnClickListener{
            viewModel.deleteDevice(device.id)
        }
        binding.btUpdate.setOnClickListener{viewModel.updateDevice(device) }
    }

}
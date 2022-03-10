package com.massive.smarthome.ui.component.detailsDevice.heater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.databinding.HeaterDetailsBinding
import com.massive.smarthome.utils.DeviceKey
import com.massive.smarthome.utils.OFF
import com.massive.smarthome.utils.ON
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HeaterDetailsFragment : Fragment() {
    private lateinit var binding: HeaterDetailsBinding
    private val viewModel: HeaterDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HeaterDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       var device = arguments?.getParcelable<HeaterDevice>(DeviceKey)!!

        initView(device)
    }
    private fun initView(device: HeaterDevice){
        binding.tvDeviceName.text = device.deviceName
        binding.sbTemp.progress = device.temperature
        binding.swMode.isChecked = device.mode == ON
        binding.tvHeaterValue.text = device.temperature.toString()
        
        binding.sbTemp.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.tvHeaterValue.text = progress.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })


        binding.btDelete.setOnClickListener{
            viewModel.deleteDevice(device.id)
            requireActivity().finish()
        }
        binding.btUpdate.setOnClickListener{

            var updatedMode =  when (binding.swMode.isChecked) {
                true -> ON
                false -> OFF
            }

            var updatedDevice = HeaterDevice(id = device.id, deviceName = device.deviceName ,
                temperature = binding.sbTemp.progress, mode = updatedMode )
            viewModel.updateDevice(updatedDevice)
            requireActivity().finish()
             }
    }

}
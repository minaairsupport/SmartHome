package com.massive.smarthome.ui.component.detailsDevice.roller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import com.massive.smarthome.databinding.LightDetailsBinding
import com.massive.smarthome.databinding.RollerDetailsBinding
import com.massive.smarthome.utils.DeviceKey
import com.massive.smarthome.utils.ON
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RollerDetailsFragment : Fragment() {
    private lateinit var binding: RollerDetailsBinding
    private val viewModel: RollerDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RollerDetailsBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       var device = arguments?.getParcelable<RollerDevice>(DeviceKey)!!

        initView(device)
    }
    private fun initView(device: RollerDevice){
        binding.sbPosition.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                binding.tvRollerValue.text = progress.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding.tvRollerValue.text = device.position.toString()
        binding.tvDeviceName.text = device.deviceName
        binding.sbPosition.progress = device.position
        binding.btDelete.setOnClickListener{
            viewModel.deleteDevice(device.id)
            requireActivity().finish()
        }
        binding.btUpdate.setOnClickListener{
            var updatedDevice = RollerDevice(id = device.id, deviceName = device.deviceName ,
                position = binding.sbPosition.progress)
            viewModel.updateDevice(updatedDevice)
            requireActivity().finish()
        }
    }

}
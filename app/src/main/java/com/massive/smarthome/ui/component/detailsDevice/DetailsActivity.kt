package com.massive.smarthome.ui.component.detailsDevice

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.massive.smarthome.R
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import com.massive.smarthome.databinding.DetailsLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity
import com.massive.smarthome.ui.component.detailsDevice.heater.HeaterDetailsFragment
import com.massive.smarthome.ui.component.detailsDevice.light.LightDetailsFragment
import com.massive.smarthome.ui.component.detailsDevice.roller.RollerDetailsFragment
import com.massive.smarthome.utils.DeviceKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseActivity() {

    private lateinit var binding: DetailsLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     val device = intent.getParcelableExtra<Device>(DeviceKey)
     val desiredFragment = device?.let { getFragment(it) }
        if (desiredFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView , desiredFragment ).commit()
        }
    }
    override fun observeViewModel() {
    }

    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)
    }


    fun getFragment(device: Device) : Fragment{
        var  fragment : Fragment? = null
        val bundle = Bundle()
        bundle.putParcelable(DeviceKey, device)
        when(device){
            is LightDevice ->  {
                var frag = LightDetailsFragment()
                    frag.arguments = bundle
                fragment = frag
            }

            is HeaterDevice ->  {
                var frag = HeaterDetailsFragment()
                frag.arguments = bundle
                fragment = frag
            }

            is RollerDevice ->  {
                var frag = RollerDetailsFragment()
                frag.arguments = bundle
                fragment = frag
            }
        }

        return fragment!!
    }
}
package com.massive.smarthome.ui.component.detailsDevice.roller

import androidx.lifecycle.viewModelScope
import com.massive.smarthome.data.DataRepositorySource
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import com.massive.smarthome.ui.base.BaseViewModel
import com.massive.smarthome.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RollerDetailsViewModel @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {


    fun deleteDevice(deviceId: Int){
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                dataRepositoryRepository.deleteDevice(deviceId)
            }
        }

    }
    fun updateDevice(device: RollerDevice){
        viewModelScope.launch {
            wrapEspressoIdlingResource {
                dataRepositoryRepository.updateRollerDevice(device)
            }
        }

    }
}
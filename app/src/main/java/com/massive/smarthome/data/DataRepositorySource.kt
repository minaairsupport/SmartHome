package com.massive.smarthome.data

import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun requestDevices(): Flow<Resource<List<DevicesItem>>>
    suspend fun deleteDevice(deviceId: Int)
    suspend fun updateLightDevice(device: LightDevice)
    suspend fun updateHeaterDevice(device: HeaterDevice)
    suspend fun updateRollerDevice(device: RollerDevice)
}
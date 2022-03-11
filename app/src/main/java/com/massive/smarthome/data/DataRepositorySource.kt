package com.massive.smarthome.data

import androidx.lifecycle.LiveData
import com.massive.smarthome.data.dto.Address
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.User
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice

interface DataRepositorySource {

    suspend fun requestDevices(): LiveData<Resource<List<DevicesItem>>>
    suspend fun deleteDevice(deviceId: Int)
    suspend fun updateLightDevice(device: LightDevice)
    suspend fun updateHeaterDevice(device: HeaterDevice)
    suspend fun updateRollerDevice(device: RollerDevice)
    fun getCurrentUser(): LiveData<User>
    fun getAddress(): LiveData<Address>
    fun updateCurrentUser(user: User): Int
    fun updateAddress(address: Address): Int

}
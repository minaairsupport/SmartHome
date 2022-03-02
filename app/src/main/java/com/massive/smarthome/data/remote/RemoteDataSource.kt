package com.massive.smarthome.data.remote

import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.device.Device

interface RemoteDataSource {
    suspend fun requestDevices(): Resource<List<Device>>
}
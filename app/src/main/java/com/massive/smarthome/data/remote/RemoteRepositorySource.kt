package com.massive.smarthome.data.remote

import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.DevicesItem

interface RemoteRepositorySource {
    suspend fun requestDevices(): Resource<List<DevicesItem>>
}
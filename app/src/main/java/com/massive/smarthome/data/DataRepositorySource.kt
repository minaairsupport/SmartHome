package com.massive.smarthome.data

import com.massive.smarthome.data.dto.device.Device
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {

    suspend fun requestDevices(): Flow<Resource<List<Device>>>
}
package com.massive.smarthome.data

import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import com.massive.smarthome.data.local.AppDao
import com.massive.smarthome.data.remote.RemoteRepository
import com.massive.smarthome.utils.heaterDeviceToDeviceItem
import com.massive.smarthome.utils.lightDeviceToDeviceItem
import com.massive.smarthome.utils.rollerDeviceToDeviceItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val remoteRepository: RemoteRepository,private val appDao: AppDao , private val ioDispatcher: CoroutineContext) : DataRepositorySource {

    override suspend fun requestDevices(): Flow<Resource<List<DevicesItem>>> {
        return flow { emit(remoteRepository.requestDevices()) }.flowOn(ioDispatcher)
    }

    override suspend fun deleteDevice(deviceId: Int) {
        return appDao.deleteDeviceById(deviceId)
    }

    override suspend fun updateLightDevice(device: LightDevice) {
         appDao.updateDevice(lightDeviceToDeviceItem(device))
    }

    override suspend fun updateHeaterDevice(device: HeaterDevice) {
        appDao.updateDevice(heaterDeviceToDeviceItem(device))
    }

    override suspend fun updateRollerDevice(device: RollerDevice) {
        appDao.updateDevice(rollerDeviceToDeviceItem(device))
    }


}
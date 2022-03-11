package com.massive.smarthome.data

import androidx.lifecycle.LiveData
import com.massive.smarthome.data.dto.Address
import com.massive.smarthome.data.dto.ApiResponse
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.User
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import com.massive.smarthome.data.local.AppDao
import com.massive.smarthome.data.remote.RemoteRepository
import com.massive.smarthome.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val remoteRepository: RemoteRepository,private val appDao: AppDao, private val ioDispatcher: CoroutineContext ) : DataRepositorySource {

    override suspend fun requestDevices(): LiveData<Resource<List<DevicesItem>>> {
        return when (appDao.getAllDevices().value.isNullOrEmpty()) {
                true -> getDevicesFromServer()
                else -> getDataLocally()
            }

    }

     fun getDevicesFromServer() =  performGetOperation(
            databaseQuery = { appDao.getAllDevices() },
            networkCall = { remoteRepository.requestDevices() },
            saveCallResult = { storeDataInDb(it)
            }
        )


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

    fun storeDataInDb(response: ApiResponse){
        response.devices?.let {
            val ids =    appDao.insertAllDevices(it as List<DevicesItem>)
        }
        response.user?.let { appDao.insertUser(it) }
        response.user?.address?.let {
            appDao.insertAddress(it)
        }
    }

    private fun getDataLocally(): LiveData<Resource<List<DevicesItem>>> =  performLocalOperation( databaseQuery = {appDao.getAllDevices()})

    override fun getCurrentUser(): LiveData<User> {
        return appDao.getUser()
    }

    override fun getAddress(): LiveData<Address> {
     return appDao.getAddress()
    }

    override fun updateCurrentUser(user: User): Int {
        return appDao.updateUser(user)
    }

    override fun updateAddress(address: Address): Int {
        return appDao.updateAddress(address)
    }


}
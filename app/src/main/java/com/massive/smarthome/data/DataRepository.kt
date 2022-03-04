package com.massive.smarthome.data

import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.remote.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(private val remoteRepository: RemoteRepository,  private val ioDispatcher: CoroutineContext) : DataRepositorySource {

    override suspend fun requestDevices(): Flow<Resource<List<DevicesItem>>> {
        return flow { emit(remoteRepository.requestDevices()) }.flowOn(ioDispatcher)
    }

}
package com.massive.smarthome.data.remote

import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.ApiResponse
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.error.NETWORK_ERROR
import com.massive.smarthome.data.error.NO_INTERNET_CONNECTION
import com.massive.smarthome.data.local.AppDao
import com.massive.smarthome.data.remote.service.DevicesService
import com.massive.smarthome.utils.NetworkConnectivitySource
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val serviceGenerator: ServiceGenerator ,
                                           private val networkConnectivity: NetworkConnectivitySource,
                                           private val appDao:AppDao ): RemoteRepositorySource {

    override suspend fun requestDevices(): Resource<List<DevicesItem>> {
         return when(appDao.getAllDevicesOrderedById().value.isNullOrEmpty()){
            true   -> getDataFromServer()
            false  -> getDataLocally()
        }
    }

    private fun getDataLocally(): Resource<List<DevicesItem>> {
       return appDao.getAllDevicesOrderedById().value?.let { Resource.Success(data = it) }!!
    }

    private suspend fun getDataFromServer(): Resource<List<DevicesItem>> {
        val devicesService = serviceGenerator.createService(DevicesService::class.java)
        return when (val response = processCall(devicesService::fetchApiResponse)) {
            is ApiResponse -> {
                // store data in room database
                storeDataInDb(response)
                Resource.Success(data = response.devices as ArrayList<DevicesItem>)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any?{
        if(!networkConnectivity.isNetworkAvailable()){
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e : IOException){
            NETWORK_ERROR
        }
    }

    private suspend fun storeDataInDb(apiResponse: ApiResponse){
        apiResponse.devices?.let { appDao.insertAllDevices(it as List<DevicesItem>) }
        apiResponse.user?.let { appDao.insertUser(it) }
        apiResponse.user?.address?.let { appDao.insertAddress(it) }
    }


}
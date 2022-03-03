package com.massive.smarthome.data.remote

import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.error.NETWORK_ERROR
import com.massive.smarthome.data.error.NO_INTERNET_CONNECTION
import com.massive.smarthome.data.remote.service.RecipesService
import com.massive.smarthome.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val serviceGenerator: ServiceGenerator , private val networkConnectivity: NetworkConnectivity): RemoteRepositorySource {

    override suspend fun requestDevices(): Resource<List<Device>> {
        val devicesService = serviceGenerator.createService(RecipesService::class.java)
        return when(val response = processCall(devicesService::fetchDevices)){
            is List<*> -> {
                Resource.Success(data = response as ArrayList<Device>)
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
}
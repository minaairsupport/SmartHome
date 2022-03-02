package com.massive.smarthome.data.remote.service

import com.massive.smarthome.data.dto.device.Device
import retrofit2.Response
import retrofit2.http.GET

interface RecipesService {
    @GET("data.json")
    suspend fun fetchDevices(): Response<List<Device>>
}
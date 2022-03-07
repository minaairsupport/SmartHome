package com.massive.smarthome.data.remote.service

import com.massive.smarthome.data.dto.ApiResponse

import retrofit2.Response
import retrofit2.http.GET

interface DevicesService {
    @GET("data.json")
    suspend fun fetchApiResponse(): Response<ApiResponse>
}
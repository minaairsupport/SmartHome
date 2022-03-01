package com.massive.smarthome.data.dto.device

import com.squareup.moshi.Json

open class Device {

    @Json(name="id")
    val id: Int? = null

    @Json(name="deviceName")
    val deviceName: String? = null

    @Json(name="productType")
    val productType: String? = null
}
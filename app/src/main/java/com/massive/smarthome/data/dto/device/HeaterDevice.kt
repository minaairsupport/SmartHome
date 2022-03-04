package com.massive.smarthome.data.dto.device

import com.squareup.moshi.Json

class HeaterDevice : Device() {

    @Json(name="temperature")
    val temperature: Int? = null

    @Json(name="mode")
    val mode: String? = null
}
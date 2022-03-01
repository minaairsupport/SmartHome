package com.massive.smarthome.data.dto.device

import com.squareup.moshi.Json

class RollerDevice(): Device() {

    @Json(name="position")
    val position: Int? = null
}
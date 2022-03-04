package com.massive.smarthome.data.dto.device

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeaterDevice(override var id: Int,
                       override var deviceName: String,
                       val temperature: Int,
                       val mode: String) : Device(), Parcelable
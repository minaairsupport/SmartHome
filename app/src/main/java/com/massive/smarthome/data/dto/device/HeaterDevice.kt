package com.massive.smarthome.data.dto.device

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeaterDevice(override val id: Int,
                       override val deviceName: String,
                       val temperature: Int,
                       val mode: String) : Device(), Parcelable
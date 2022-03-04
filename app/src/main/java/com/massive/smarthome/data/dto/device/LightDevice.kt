package com.massive.smarthome.data.dto.device

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LightDevice(override val id: Int,
					   override val deviceName: String,
					   val intensity: String,
					   val mode: String) : Device(), Parcelable

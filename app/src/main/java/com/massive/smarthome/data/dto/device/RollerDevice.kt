package com.massive.smarthome.data.dto.device

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RollerDevice(override val id: Int,
                        override val deviceName: String,
                        val position: Int) : Device(), Parcelable
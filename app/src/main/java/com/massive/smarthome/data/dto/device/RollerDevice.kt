package com.massive.smarthome.data.dto.device

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RollerDevice(override var id: Int,
                        override var deviceName: String,
                        val position: Int) : Device(), Parcelable
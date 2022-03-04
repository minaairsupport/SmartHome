package com.massive.smarthome.data.dto.device


import android.os.Parcelable


sealed class Device : Parcelable {
    abstract val id: Int
    abstract val deviceName: String
}
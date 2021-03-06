package com.massive.smarthome.data.dto.device


import android.os.Parcelable


sealed class Device : Parcelable {
    abstract var id: Int
    abstract var deviceName: String
}
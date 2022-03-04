package com.massive.smarthome.utils

import androidx.room.TypeConverter
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import java.text.SimpleDateFormat


fun convertResponse(devicesApi: List<DevicesItem>): List<Device> {
    return devicesApi.map {
        when (it.productType) {
            LIGHT_TYPE -> LightDevice(
                id = it.id!!,
                deviceName = it.deviceName!!,
                intensity = it.intensity!!,
                mode = it.mode!!
            )

            HEATER_TYPE -> HeaterDevice(
                id = it.id!!,
                deviceName = it.deviceName!!,
                mode = it.mode!!,
                temperature = it.temperature!!
            )

            ROLLER_TYPE -> RollerDevice(
                id = it.id!!,
                deviceName = it.deviceName!!,
                position = it.position!!
            )
            else -> throw Exception()
        }
    }
}

@TypeConverter
fun convertLongToDate(dataLong: Long?): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    return simpleDateFormat.format(dataLong)
}

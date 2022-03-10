package com.massive.smarthome.utils

import androidx.room.TypeConverter
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.data.dto.device.HeaterDevice
import com.massive.smarthome.data.dto.device.LightDevice
import com.massive.smarthome.data.dto.device.RollerDevice
import java.text.SimpleDateFormat


fun convertResponse(devicesApi: List<DevicesItem>): ArrayList<Device> {
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
    } as ArrayList<Device>
}

    fun lightDeviceToDeviceItem(device: LightDevice): DevicesItem {
        var devicesItem = DevicesItem(id = device.id, deviceName = device.deviceName)
         devicesItem.intensity = device.intensity
         devicesItem.mode = device.mode
        devicesItem.productType = LIGHT_TYPE
         return devicesItem
    }

    fun heaterDeviceToDeviceItem(device: HeaterDevice): DevicesItem{
        var devicesItem = DevicesItem(id = device.id, deviceName = device.deviceName)
        devicesItem.temperature = device.temperature
        devicesItem.mode = device.mode
        devicesItem.productType = HEATER_TYPE
        return devicesItem
    }

    fun rollerDeviceToDeviceItem(device: RollerDevice): DevicesItem{
        var devicesItem = DevicesItem(id = device.id, deviceName = device.deviceName)
        devicesItem.position = device.position
        devicesItem.productType = ROLLER_TYPE
        return devicesItem
    }

@TypeConverter
fun convertLongToDate(dataLong: Long?): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    return simpleDateFormat.format(dataLong)
}

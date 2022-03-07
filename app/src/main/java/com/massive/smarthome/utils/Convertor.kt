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
        var devicesItem = DevicesItem()
         devicesItem.deviceName = device.deviceName
         devicesItem.id = device.id
         devicesItem.intensity = device.intensity
         devicesItem.mode = device.mode
         return devicesItem
    }

    fun heaterDeviceToDeviceItem(device: HeaterDevice): DevicesItem{
        var devicesItem = DevicesItem()
        devicesItem.deviceName = device.deviceName
        devicesItem.id = device.id
        devicesItem.temperature = device.temperature
        devicesItem.mode = device.mode
        return devicesItem
    }

    fun rollerDeviceToDeviceItem(device: RollerDevice): DevicesItem{
        var devicesItem = DevicesItem()
        devicesItem.deviceName = device.deviceName
        devicesItem.id = device.id
        devicesItem.position = device.position
        return devicesItem
    }

@TypeConverter
fun convertLongToDate(dataLong: Long?): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
    return simpleDateFormat.format(dataLong)
}

package com.massive.smarthome.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.massive.smarthome.data.dto.Address
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.User

@Dao
interface AppDao {
    // all database methods should be here

    // Queries
    @Query("SELECT * FROM device WHERE productType = :type")
    fun getDevicesByType(type: String): LiveData<List<DevicesItem>>

    @Query("SELECT * FROM device ORDER BY productType ASC")
    fun getAllDevicesOrderedByType(): LiveData<List<DevicesItem>>

    @Query("SELECT * FROM device")
    fun getAllDevices(): LiveData<List<DevicesItem>>

    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

    @Query("SELECT * FROM address")
    fun getAddress(): LiveData<Address>


    //Insertion
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllDevices(devices: List<DevicesItem>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user : User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAddress(address : Address)

    // Delete
    @Query("DELETE FROM device")
    fun deleteAllDevices()

    @Query("DELETE FROM device WHERE id = :id")
    fun deleteDeviceById(id: Int)

    @Delete
    fun deleteDeviceByObj(device: DevicesItem)

    @Query("DELETE FROM user")
    fun deleteUser()

    @Query("DELETE FROM address")
    fun deleteAddress()

    @Update
    fun updateUser(user: User?): Int

    @Update
    fun updateAddress(address: Address): Int


    @Update
    fun updateDevice(devic: DevicesItem?): Int
}
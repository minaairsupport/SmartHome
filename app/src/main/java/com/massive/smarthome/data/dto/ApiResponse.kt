package com.massive.smarthome.data.dto

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class ApiResponse(

	@Json(name="devices")
	var devices: List<DevicesItem?>? = null,

	@Json(name="user")
	var user: User? = null
)

@Entity(tableName = "device")
 class DevicesItem(

	@PrimaryKey
	@Json(name="id")
	var id: Int,

	@Json(name="deviceName")
	var deviceName: String,

	@Json(name="productType")
	var productType: String? = null,

	@Json(name="mode")
	var mode: String? = null,

	@Json(name="temperature")
	var temperature: Int? = null,

	@Json(name="position")
	var position: Int? = null,

	@Json(name="intensity")
	var intensity: Int? = null,
	)

@Entity(tableName = "address")
data class Address(

	@PrimaryKey(autoGenerate = true)
	var id: Int? = null,

	@Json(name="country")
	var country: String? = null,

	@Json(name="city")
	var city: String? = null,

	@Json(name="street")
	var street: String? = null,

	@Json(name="postalCode")
	var postalCode: Int? = null,

	@Json(name="streetCode")
	var streetCode: String? = null
)

@Entity(tableName = "user")
data class User(

	@PrimaryKey(autoGenerate = true)
	var id: Int? = null,

	@Json(name="firstName")
	var firstName: String? = null,

	@Json(name="lastName")
	var lastName: String? = null,

	@Json(name="address")
	@Ignore
	var address: Address? = null,

	@Json(name="birthDate")
	var birthDate: Long? = null
)

package com.massive.smarthome.data.dto

import com.squareup.moshi.Json

data class JsonResponse(

	@Json(name="devices")
	val devices: List<DevicesItem?>? = null,

	@Json(name="user")
	val user: User? = null
)

data class DevicesItem(

	@Json(name="mode")
	val mode: String? = null,

	@Json(name="temperature")
	val temperature: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="deviceName")
	val deviceName: String? = null,

	@Json(name="productType")
	val productType: String? = null,

	@Json(name="position")
	val position: Int? = null,

	@Json(name="intensity")
	val intensity: Int? = null
)

data class Address(

	@Json(name="country")
	val country: String? = null,

	@Json(name="city")
	val city: String? = null,

	@Json(name="street")
	val street: String? = null,

	@Json(name="postalCode")
	val postalCode: Int? = null,

	@Json(name="streetCode")
	val streetCode: String? = null
)

data class User(

	@Json(name="firstName")
	val firstName: String? = null,

	@Json(name="lastName")
	val lastName: String? = null,

	@Json(name="address")
	val address: Address? = null,

	@Json(name="birthDate")
	val birthDate: Long? = null
)

package com.massive.smarthome.data.dto.device

import com.squareup.moshi.Json

class LightDevice : Device(){

	@Json(name="intensity")
	val intensity: Int? = null

	@Json(name="mode")
	val mode: String? = null

}

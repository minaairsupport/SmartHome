package com.massive.smarthome.data.error

import java.lang.Exception


const val NO_INTERNET_CONNECTION = -1
const val NETWORK_ERROR = -2
const val DEFAULT_ERROR = -3
const val PASS_WORD_ERROR = -101
const val USER_NAME_ERROR = -102
const val CHECK_YOUR_FIELDS = -103

class Error(val code:Int, val description: String) {
    constructor(exception: Exception):this(code = DEFAULT_ERROR , description = exception.message ?: "")

}
package com.massive.smarthome.usecase.errors

import com.massive.smarthome.data.error.Error
interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
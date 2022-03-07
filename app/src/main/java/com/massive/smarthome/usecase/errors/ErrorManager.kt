package com.massive.smarthome.usecase.errors

import com.massive.smarthome.data.error.mapper.ErrorMapper
import javax.inject.Inject
import com.massive.smarthome.data.error.Error

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {

    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
package com.massive.smarthome.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.ApiResponse
import com.massive.smarthome.data.dto.DevicesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

fun <T, A> performGetOperation(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Resource<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        val source = databaseQuery.invoke().map {
            Resource.Success(data = it)
        }
        emitSource(source as LiveData<Resource<T>>)
        val responseStatus = networkCall.invoke()
        if (responseStatus.data != null) {
            saveCallResult(responseStatus.data!!)

        } else if (responseStatus.errorCode != null) {
            emit(Resource.DataError(errorCode= responseStatus.errorCode))
            emitSource(source)
        }
    }

fun <T> performLocalOperation(
    databaseQuery: () -> LiveData<T>
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        val source = databaseQuery.invoke().map {
            Resource.Success( data = it)
        }
        emitSource(source as LiveData<Resource<T>>)
    }
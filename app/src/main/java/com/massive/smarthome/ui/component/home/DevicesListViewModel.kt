package com.massive.smarthome.ui.component.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.massive.smarthome.data.DataRepositorySource
import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.ui.base.BaseViewModel
import com.massive.smarthome.utils.SingleEvent
import com.massive.smarthome.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DevicesListViewModel @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    // data exposed as  LiveData but locally as MutableLiveData
    private val devicesLiveDataPrivate = MutableLiveData<Resource<List<DevicesItem>>>()
    val devicesLiveData: LiveData<Resource<List<DevicesItem>>> get() = devicesLiveDataPrivate

    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    fun getDevices(){
        viewModelScope.launch {
            dataRepositoryRepository.requestDevices().asFlow().collect{
                devicesLiveDataPrivate.value = it
            }
        }
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = SingleEvent(error.description)
    }
}
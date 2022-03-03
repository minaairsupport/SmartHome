package com.massive.smarthome.ui.component.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.massive.smarthome.data.DataRepositorySource
import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.ui.base.BaseViewModel
import com.massive.smarthome.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DevicesListViewModel @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    // data exposed as  LiveData but locally as MutableLiveData
    private val devicesLiveDataPrivate = MutableLiveData<Resource<List<Device>>>()
    val devicesLiveData: LiveData<Resource<List<Device>>> get() = devicesLiveDataPrivate

    fun getDevices(){
        viewModelScope.launch {
                    devicesLiveDataPrivate.value = Resource.Loading()
                    wrapEspressoIdlingResource {
                        dataRepositoryRepository.requestDevices().collect{
                            devicesLiveDataPrivate.value= it
                        }
                    }
        }
    }
}
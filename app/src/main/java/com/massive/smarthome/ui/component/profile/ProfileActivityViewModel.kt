package com.massive.smarthome.ui.component.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.massive.smarthome.data.DataRepositorySource
import com.massive.smarthome.data.dto.Address
import com.massive.smarthome.data.dto.User

import com.massive.smarthome.ui.base.BaseViewModel
import com.massive.smarthome.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileActivityViewModel @Inject constructor(private val dataRepositoryRepository: DataRepositorySource) : BaseViewModel() {

    val userLiveData = dataRepositoryRepository.getCurrentUser()
    val userAddressData = dataRepositoryRepository.getAddress()

    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    fun updateUserAddress(user: User, address: Address) {
        GlobalScope.launch {
            if (dataRepositoryRepository.updateCurrentUser(user) == 1 && dataRepositoryRepository.updateAddress(address) == 1)
                showToastPrivate.postValue( SingleEvent("your Profile is updated Successfully"))
            else
                showToastPrivate.postValue( SingleEvent("Error while update profile "))


        }
    }
}
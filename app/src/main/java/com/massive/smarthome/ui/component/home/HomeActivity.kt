package com.massive.smarthome.ui.component.home

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.massive.smarthome.data.Resource
import com.massive.smarthome.data.dto.device.Device

import com.massive.smarthome.databinding.HomeLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity
import com.massive.smarthome.ui.component.profile.ProfileActivity
import com.massive.smarthome.utils.*
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.massive.smarthome.ui.component.home.adapter.DevicesListAdapter

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: HomeLayoutBinding
    private val devicesListViewModel: DevicesListViewModel by viewModels()
    private lateinit var devicesAdapter: DevicesListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivProfile.setOnClickListener{ navigateToProfilePage() }
        devicesListViewModel.getDevices()
    }

    override fun observeViewModel() {
        observe(devicesListViewModel.devicesLiveData, ::handleDevicesList)
        observeToast(devicesListViewModel.showToast)
    }

    override fun initViewBinding() {
        binding = HomeLayoutBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)
    }

    private fun navigateToProfilePage() {
        val profileScreenIntent = Intent(this, ProfileActivity::class.java)
        startActivity(profileScreenIntent)
    }
    fun handleDevicesList(resource: Resource<List<Device>>) {
        when (resource) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> {resource.data?.let { bindListData(devices = it) }}
            is Resource.DataError -> {
                showDataView(false)
                resource.errorCode?.let {
                    devicesListViewModel.showToastMessage(it)
                }
            }

        }
    }

    private fun bindListData(devices: List<Device>) {
        if (!(devices.isNullOrEmpty())) {
            devicesAdapter = DevicesListAdapter(devicesListViewModel, devices)
            binding.rvDevicesList.adapter = devicesAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rvDevicesList.toGone()

    }

    private fun showDataView(show: Boolean) {
        binding.rvDevicesList.visibility = if (show) View.VISIBLE else View.GONE
        binding.tvNoData.visibility = if (!show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}



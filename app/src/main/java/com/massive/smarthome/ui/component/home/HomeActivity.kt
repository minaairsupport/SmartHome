package com.massive.smarthome.ui.component.home

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
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

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: HomeLayoutBinding
    private lateinit var devicesListViewModel: DevicesListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        devicesListViewModel = ViewModelProvider(this).get(DevicesListViewModel::class.java)
        binding.ivProfile.setOnClickListener{ navigateToProfilePage() }
    }

    override fun observeViewModel() {
        observe(devicesListViewModel.devicesLiveData, ::handleDevicesList)
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
        when(resource){
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> { }
            is Resource.DataError -> {}// show loading
        }

    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()

    }

    private fun observeSnackBarMessages(event: LiveData<SingleEvent<Any>>) {
        binding.root.setupSnackbar(this, event, Snackbar.LENGTH_LONG)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
}



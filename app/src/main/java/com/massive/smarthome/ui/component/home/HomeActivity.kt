package com.massive.smarthome.ui.component.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.massive.smarthome.data.Resource

import com.massive.smarthome.databinding.HomeLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity
import com.massive.smarthome.ui.component.profile.ProfileActivity
import com.massive.smarthome.utils.*
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.massive.smarthome.R
import com.massive.smarthome.data.dto.DevicesItem
import com.massive.smarthome.data.dto.device.Device
import com.massive.smarthome.ui.base.listeners.RecyclerItemListener
import com.massive.smarthome.ui.component.detailsDevice.DetailsActivity
import com.massive.smarthome.ui.component.home.adapter.DevicesListAdapter
import kotlinx.coroutines.*

@AndroidEntryPoint
class HomeActivity : BaseActivity() , RecyclerItemListener<Device> {

    private lateinit var binding: HomeLayoutBinding
    private val devicesListViewModel: DevicesListViewModel by viewModels()
    private lateinit var devicesAdapter: DevicesListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.empty_tall_divider)!!)
        binding.rvDevicesList.addItemDecoration(decorator)
        binding.ivProfile.setOnClickListener{ navigateToProfilePage() }
        devicesListViewModel.getDevices()
    }

    override fun onResume() {
        super.onResume()
        val types = resources.getStringArray(R.array.device_types)
        val arrayAdapter = ArrayAdapter(this , R.layout.dropdown_item , types )
        binding.acDeviceType.setAdapter( arrayAdapter)
        binding.acDeviceType.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            if(this::devicesAdapter.isInitialized)
            devicesAdapter?.filterByType(selectedItem)
        }
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
    private fun handleDevicesList(resource: Resource<List<DevicesItem>>) {
        when (resource) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> resource.data?.let { bindListData(devices = it) }
            is Resource.DataError -> {
                showDataView(false)
                resource.errorCode?.let {
                    devicesListViewModel.showToastMessage(it)
                }
            }

        }
    }

    private fun bindListData(devices: List<DevicesItem>) {
        if (!(devices.isNullOrEmpty())) {
            devicesAdapter = DevicesListAdapter(devicesListViewModel, convertResponse(devices) , this)
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

    override fun onItemSelected(item: Device) {
        val detailScreenIntent = Intent(this, DetailsActivity::class.java)
        detailScreenIntent.putExtra(DeviceKey , item)
        startActivity(detailScreenIntent)
    }
}



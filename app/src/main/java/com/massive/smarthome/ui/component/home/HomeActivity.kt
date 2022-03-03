package com.massive.smarthome.ui.component.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

import com.massive.smarthome.databinding.HomeLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity
import com.massive.smarthome.ui.component.profile.ProfileActivity
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
        observe(devicesListViewModel.recipesLiveData, ::handleRecipesList)
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
}
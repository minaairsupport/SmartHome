package com.massive.smarthome.ui.component.home

import com.massive.smarthome.databinding.HomeLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: HomeLayoutBinding
    override fun observeViewModel() {

    }

    override fun initViewBinding() {
        binding = HomeLayoutBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)
    }
}
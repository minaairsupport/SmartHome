package com.massive.smarthome.ui.component.profile

import android.os.Bundle
import com.massive.smarthome.databinding.ProfileLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity

class ProfileActivity(): BaseActivity() {
    private lateinit var binding: ProfileLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btSave.setOnClickListener{}
        binding.ibBack.setOnClickListener{finish()}
    }

    private fun doSaveForm(){
        //TODO(" do the save form here ")
    }

    override fun observeViewModel() {

    }

    override fun initViewBinding() {
       binding = ProfileLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
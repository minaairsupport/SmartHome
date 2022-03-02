package com.massive.smarthome.ui.component.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.AppCompatImageView
import com.massive.smarthome.R
import com.massive.smarthome.databinding.HomeLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity
import com.massive.smarthome.ui.component.profile.ProfileActivity

class HomeActivity : BaseActivity() {

    private lateinit var binding: HomeLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.ivProfile.setOnClickListener{ navigateToProfilePage() }
    }

    override fun observeViewModel() {

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
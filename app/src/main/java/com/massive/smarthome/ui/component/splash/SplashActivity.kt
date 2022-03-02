package com.massive.smarthome.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.massive.smarthome.databinding.SplashLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity
import com.massive.smarthome.ui.component.home.HomeActivity

class SplashActivity : BaseActivity() {

    private lateinit var binding: SplashLayoutBinding
    val SPLASH_DELAY = 3000


    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToHomePage()

    }

    private fun navigateToHomePage() {
        Handler(Looper.getMainLooper()).postDelayed({
            val nextScreenIntent = Intent(this, HomeActivity::class.java)
            startActivity(nextScreenIntent)
            finish()
        }, SPLASH_DELAY.toLong())
    }

    override fun observeViewModel() {
    }
}
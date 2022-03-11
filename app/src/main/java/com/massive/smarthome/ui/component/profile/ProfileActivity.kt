package com.massive.smarthome.ui.component.profile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.google.android.material.snackbar.Snackbar
import com.massive.smarthome.data.dto.Address
import com.massive.smarthome.data.dto.User
import com.massive.smarthome.databinding.ProfileLayoutBinding
import com.massive.smarthome.ui.base.BaseActivity
import com.massive.smarthome.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity() {
    private lateinit var binding: ProfileLayoutBinding
    private val profileViewModel: ProfileActivityViewModel by viewModels()
    private var userId: Int? = null
    private var addressId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btSave.setOnClickListener{doSaveForm()}
        binding.ibBack.setOnClickListener{finish()}

    }

    private fun doSaveForm(){
        val user = User(
            id =  userId,
            firstName = binding.teFirstName.text.toString(),
            lastName = binding.teLastName.text.toString(),
            birthDate = convertDateToLong(binding.teBirthday.text.toString())
        )
        val address = Address(
            id =  addressId,
            country=   binding.teCountry.text.toString(),
            street = binding.teStreet.text.toString(),
            city = binding.teCity.text.toString(),
            streetCode = binding.teStreetCode.text.toString(),
            postalCode = binding.tePostalCode.text.toString().toInt(),
        )
        profileViewModel.updateUserAddress(user , address)
    }

    override fun observeViewModel() {
        observe(profileViewModel.userLiveData, ::handleInitUser)
        observe(profileViewModel.userAddressData, ::handleInitUserAddress)
        observeToast(profileViewModel.showToast)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }
    private fun handleInitUserAddress(address: Address) {
        addressId = address.id
        binding.teCountry.setText(address.country)
        binding.teStreet.setText(address.street)
        binding.teCity.setText(address.city)
        address.postalCode?.let { binding.tePostalCode.setText(it.toString()) }
        binding.teStreetCode.setText(address.streetCode)
    }

    private fun handleInitUser(user: User) {
        userId = user.id
        binding.teFirstName.setText(user.firstName)
        binding.teLastName.setText(user.lastName)
        binding.teBirthday.setText(convertLongToDate(user.birthDate))
    }

    override fun initViewBinding() {
       binding = ProfileLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
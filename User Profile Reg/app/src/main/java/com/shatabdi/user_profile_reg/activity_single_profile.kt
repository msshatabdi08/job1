package com.shatabdi.user_profile_reg

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shatabdi.user_profile_reg.databinding.ActivitySingleProfileBinding
import kotlin.getValue

class ActivitySingleProfile : AppCompatActivity() {

    private lateinit var binding: ActivitySingleProfileBinding
    private val viewModel: UserProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val profileId = intent.getIntExtra("PROFILE_ID", -1)
        if (profileId == -1) {
            finish()
            return
        }

        // Observe and display the profile details
        viewModel.getProfileById(profileId).observe(this) { profile ->
            profile?.let {
                binding.tvName.text = "Name: ${it.name}"
                binding.tvEmail.text = "Email: ${it.email}"
                binding.tvBirthDate.text = "Birth Date: ${it.dateOfBirth}"
                binding.tvMobile.text = "Phone: ${it.mobile}"
                binding.tvAddress.text = "Address: ${it.address.ifEmpty { "N/A" }}"
            }
        }

        // Edit button → go to ActivityAddProfile in edit mode
        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, activity_add_profile::class.java)
            intent.putExtra("PROFILE_ID", profileId)
            startActivity(intent)
        }

        // Delete button → confirm then delete
        binding.btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete Profile")
                .setMessage("Are you sure you want to delete this profile?")
                .setPositiveButton("Delete") { _, _ ->
                    viewModel.deleteProfileById(profileId)
                    finish()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}

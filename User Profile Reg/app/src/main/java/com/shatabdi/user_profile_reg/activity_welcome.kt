package com.shatabdi.user_profile_reg

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shatabdi.user_profile_reg.databinding.ActivityWelcomeBinding


class activity_welcome : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnProfileList.setOnClickListener {
            startActivity(Intent(this, ActivityProfileList::class.java))
        }
    }
}
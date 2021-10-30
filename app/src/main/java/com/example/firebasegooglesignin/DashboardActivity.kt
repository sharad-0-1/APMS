package com.example.firebasegooglesignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasegooglesignin.databinding.ActivityDashboardBinding
import com.example.firebasegooglesignin.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.levelsImageButton.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,ReadData::class.java))
        }
        binding.infoImageButton.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,Info1Activity::class.java))
        }
        binding.profileImageButton.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,ProfileActivity::class.java))
        }
        binding.statsImageButton.setOnClickListener {
            startActivity(Intent(this@DashboardActivity,StatisticsLocationSearch::class.java))
        }
    }
}
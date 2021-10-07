package com.example.firebasegooglesignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasegooglesignin.databinding.ActivityDashboardBinding
import com.example.firebasegooglesignin.databinding.ActivityInfo2Binding

class Info2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityInfo2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.todashboard.setOnClickListener {
            startActivity(Intent(this@Info2Activity,DashboardActivity::class.java))
            finish()
        }
    }
}
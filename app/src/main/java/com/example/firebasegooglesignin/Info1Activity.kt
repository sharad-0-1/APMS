package com.example.firebasegooglesignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebasegooglesignin.databinding.ActivityDashboardBinding
import com.example.firebasegooglesignin.databinding.ActivityInfo1Binding

class Info1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityInfo1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfo1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.next.setOnClickListener {
            startActivity(Intent(this@Info1Activity,Info2Activity::class.java))
            finish()
        }
    }
}
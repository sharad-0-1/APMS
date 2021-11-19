package com.example.firebasegooglesignin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasegooglesignin.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {

    private lateinit var binding : ActivityReadDataBinding

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener{
            val userName : String = binding.etusername.text.toString()
            if (userName.isNotEmpty()){

                readData(userName)

            }else{
                Toast.makeText(this, "Please Enter The location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun readData(showGraph: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(showGraph).get().addOnSuccessListener{

            if (it.exists()){

                val latitude = it.child("latitude").value
                val longitude = it.child("longitude").value
                val temp = it.child("temp").value
                val humidity = it.child("humidity").value
                val co2 = it.child("co2").value
                val co = it.child("co").value
                val so2 = it.child("so2").value
                val no2 = it.child("no2").value
                val methane = it.child("methane").value
                val o3 = it.child("o3").value


                binding.etusername.text.clear()
                binding.lat.text = latitude.toString()+" °"
                binding.longi.text = longitude.toString()+" °"
                binding.temp.text = temp.toString()+" °C"
                binding.humi.text = humidity.toString()+" %"
                binding.co2.text = co2.toString()+ " ppm"
                binding.co.text = co.toString()+ " ppm"
                binding.so2.text = so2.toString()+ " ppm"
                binding.no2.text = no2.toString()+ " ppm"
                binding.methane.text = methane.toString()+ " ppm"
                binding.o3.text = o3.toString()+ " ppm"

            }else{

                Toast.makeText(this, "Location doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{

            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()

        }
    }
}
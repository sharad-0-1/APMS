package com.example.firebasegooglesignin

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.firebasegooglesignin.databinding.ActivityReadDataBinding
import com.example.firebasegooglesignin.databinding.ActivityStatisticsLocationSearchBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class StatisticsLocationSearch : AppCompatActivity() {


    private lateinit var binding: ActivityStatisticsLocationSearchBinding

    private lateinit var database: DatabaseReference
    private lateinit var pieChart: PieChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsLocationSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {
            val userName: String = binding.etusername.text.toString()
            if (userName.isNotEmpty()) {

                readData(userName)

            } else {
                Toast.makeText(this, "Please Enter The location", Toast.LENGTH_SHORT).show()
            }
        }
        pieChart = findViewById(R.id.co22)



    }


    private fun initPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.text = ""
        //hollow pie chart
        pieChart.isDrawHoleEnabled = false
        pieChart.setTouchEnabled(false)
        pieChart.setDrawEntryLabels(false)
        //adding padding
        pieChart.setExtraOffsets(20f, 0f, 20f, 20f)
        pieChart.setUsePercentValues(true)
        pieChart.isRotationEnabled = false
        pieChart.setDrawEntryLabels(false)
        pieChart.legend.orientation = Legend.LegendOrientation.VERTICAL
//        pieChart.legend.formSize = pieChart.legend.formSize
        pieChart.legend.formSize=pieChart.legend.formSize*2
        pieChart.legend.isWordWrapEnabled = true

    }

    private fun setDataToPieChart( a: Float,b: Float,c: Float,d: Float,e: Float,f: Float) {
        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        var sum: Float
        sum = (a+b+c+d+e+f)/6.0f
        dataEntries.add(PieEntry(a/sum * 100, "Co2"))
        dataEntries.add(PieEntry(b/sum * 100, "Co"))
        dataEntries.add(PieEntry(c/sum * 100, "So2"))
        dataEntries.add(PieEntry(d/sum * 100, "No2"))
        dataEntries.add(PieEntry(e/sum * 100, "CH3"))
        dataEntries.add(PieEntry(f/sum * 100, "O3"))

        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#2A396D"))
        colors.add(Color.parseColor("#4FBFC2"))
        colors.add(Color.parseColor("#FFF09F"))
        colors.add(Color.parseColor("#F2857D"))
        colors.add(Color.parseColor("#6E5280"))
        colors.add(Color.parseColor("#4F3363"))

        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)

        // In Percentage
        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 1f
        dataSet.colors = colors
        pieChart.data = data
        data.setValueTextSize(15f)
        pieChart.setExtraOffsets(5f, 10f, 5f, 10f)
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        //create hole in center
        pieChart.holeRadius = 33f
        pieChart.transparentCircleRadius = 35f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)


        //add text in center
        pieChart.setDrawCenterText(true);
        pieChart.centerText = "PPM"



        pieChart.invalidate()

    }

    @SuppressLint("SetTextI18n")
    private fun readData(showGraph: String) {
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(showGraph).get().addOnSuccessListener {

            if (it.exists()) {

//                val latitude = it.child("latitude").value
//                val longitude = it.child("longitude").value
//                val temp = it.child("temp").value
//                val humidity = it.child("humidity").value
                val co2 = it.child("co2").value
                val co = it.child("co").value
                val so2 = it.child("so2").value
                val no2 = it.child("no2").value
                val ch3 = it.child("methane").value
                val o3 = it.child("o3").value


                binding.etusername.text.clear()
                pieChart.visibility = View.VISIBLE
                initPieChart()
                setDataToPieChart(co2.toString().toFloat(),co.toString().toFloat(),so2.toString().toFloat(),no2.toString().toFloat(),ch3.toString().toFloat(),o3.toString().toFloat())

            } else {

                Toast.makeText(this, "Location doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {

            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()

        }
    }
}
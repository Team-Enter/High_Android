package com.enter.high_v1.school

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.enter.high_v1.R
import com.enter.high_v1.databinding.FragmentInfoDepartBinding

class InfoDepartFragment(private val schoolInfo: SchoolInfoResponse) : Fragment() {
    private lateinit var binding: FragmentInfoDepartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoDepartBinding.inflate(layoutInflater, container, false)
        setDepartData()
        return binding.root
    }

    private fun setDepartData() {
        for (depart in schoolInfo.lesson) {
            val data = depart.split(" ")
            val tableRow = TableRow(activity)
            tableRow.setBackgroundColor(Color.parseColor("#D9D9D9"))
            tableRow.weightSum = 9F

            for (i in 0..2) {
                val textView = TextView(activity)
                textView.text = data[i]
                textView.setTextAppearance(R.style.text_depart_table_content)
                tableRow.addView(textView)
            }

            binding.layDepartTable.addView(tableRow)
        }
    }
}
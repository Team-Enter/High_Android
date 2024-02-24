package com.enter.high_v1.school

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enter.high_v1.R
import com.enter.high_v1.databinding.FragmentInfoSummaryBinding

class InfoSummaryFragment(private val schoolInfo: SchoolInfoResponse) : Fragment() {
    private lateinit var binding: FragmentInfoSummaryBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoSummaryBinding.inflate(layoutInflater, container, false)
        setSummaryData()
        return binding.root
    }

    private fun setSummaryData() {
        binding.textSummaryContentDay.text = schoolInfo.date
        binding.textSummaryContentType.text = schoolInfo.Etype
        binding.textSummaryContentGender.text = schoolInfo.gender
        binding.textSummaryContentNumber.text = schoolInfo.phone
        binding.textSummaryContentPage.text = schoolInfo.link
        binding.textSummaryContentPlace.text = schoolInfo.location
    }
}
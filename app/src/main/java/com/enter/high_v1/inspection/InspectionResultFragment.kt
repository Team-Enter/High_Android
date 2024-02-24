package com.enter.high_v1.inspection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enter.high_v1.MainActivity
import com.enter.high_v1.R
import com.enter.high_v1.databinding.FragmentInspectionResultBinding

class InspectionResultFragment(val first: String, val second: String) : Fragment() {
    private lateinit var binding: FragmentInspectionResultBinding
    private val mainActivity = activity as MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInspectionResultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textInspectionRNick.text = mainActivity.userData.nickname
        binding.textInspectionRFirst.text = first
        binding.textInspectionRSecond.text = second

        binding.btnInspectionRRecommend.setOnClickListener {
            mainActivity.addFragment(6)
        }

        binding.imgInspectionRProfile.setOnClickListener {
            mainActivity.addFragment(2)
        }
    }
}
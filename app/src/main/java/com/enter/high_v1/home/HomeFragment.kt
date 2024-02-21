package com.enter.high_v1.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.enter.high_v1.MainActivity
import com.enter.high_v1.R
import com.enter.high_v1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeList = mutableListOf<HomeListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        getHomeList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeAdapter(homeList)
        binding.recyclerHome.adapter = adapter
        binding.recyclerHome.layoutManager = LinearLayoutManager(activity)

        binding.layHomeInspection.setOnClickListener {
            MainActivity().addFragment(2)
        }

        binding.textHomeName.text = MainActivity().userData.nickname
    }

    private fun getHomeList() {
        for (a in 0..5)
            homeList.add(HomeListData("특목고", "대덕소프트웨어마이스터고등학교", "대전광역시 유성구"))
    }
}
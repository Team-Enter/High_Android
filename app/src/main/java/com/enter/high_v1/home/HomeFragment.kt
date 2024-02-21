package com.enter.high_v1.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.enter.high_v1.ApiProvider
import com.enter.high_v1.MainActivity
import com.enter.high_v1.R
import com.enter.high_v1.ServerApi
import com.enter.high_v1.Token
import com.enter.high_v1.databinding.FragmentHomeBinding
import com.enter.high_v1.mypage.MyPageData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeList = mutableListOf<HomeListData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        getHomeList()
        getUserData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HomeAdapter(homeList)
        binding.recyclerHome.adapter = adapter
        binding.recyclerHome.layoutManager = LinearLayoutManager(activity)

        binding.layHomeInspection.setOnClickListener {
            MainActivity().addFragment(1)
        }

        binding.imgHomeProfile.setOnClickListener {
            MainActivity().addFragment(2)
        }
    }

    private fun getHomeList() {
        for (a in 0..5)
            homeList.add(HomeListData("특목고", "대덕소프트웨어마이스터고등학교", "대전광역시 유성구"))
    }

    private fun getUserData() {
        val apiProvider = ApiProvider.getInstance().create(ServerApi::class.java)
        val token = "Bearer " + Token().getToken()
        apiProvider.getUserInfo(token).enqueue(object : Callback<MyPageData> {
            override fun onResponse(call: Call<MyPageData>, response: Response<MyPageData>) {
                if (response.isSuccessful) {
                    MainActivity().userData = response.body()!!
                    binding.textHomeName.text = response.body()!!.nickname
                } else {
                    Log.d("server", response.code().toString())
                    Toast.makeText(activity, "정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<MyPageData>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
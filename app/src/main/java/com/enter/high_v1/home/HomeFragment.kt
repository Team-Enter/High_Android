package com.enter.high_v1.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.enter.high_v1.MainActivity
import com.enter.high_v1.MyApplication
import com.enter.high_v1.databinding.FragmentHomeBinding
import java.time.LocalDate

class HomeFragment : Fragment(), HomeAdapter.SchoolRecommendClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val homeList = mutableListOf<HomeListData>()
    private val adapter = HomeAdapter(homeList, this)
    // private val mainActivity = activity as MainActivity
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        getHomeList()
        // Handler().postDelayed({getUserData()}, 500)

        binding.recyclerHome.adapter = adapter
        binding.recyclerHome.layoutManager = LinearLayoutManager(activity)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainActivity = activity as MainActivity

        val userData = MainActivity().getUserData()
        Log.d("user", userData.toString())
        binding.textHomeName.text = userData.nickname + "님"

        binding.layHomeInspection.setOnClickListener {
            mainActivity.addFragment(1)
        }

        binding.imgHomeProfile.setOnClickListener {
             mainActivity.addFragment(2)
        }

    }

    private fun getHomeList() {
        homeList.add(HomeListData("특목고", "대덕소프트웨어마이스터고등학교", "대전광역시 유성구"))
        homeList.add(HomeListData("특목고", "대구소프트웨어마이스터고등학교", "대구광역시 수성구"))
        homeList.add(HomeListData("특목고", "부산소프트웨어마이스터고등학교", "부산광역시 ㅇㅇ구"))
        homeList.add(HomeListData("특목고", "광주소프트웨어마이스터고등학교", "광주광역시 ㅇㅇ구"))
        adapter.notifyDataSetChanged()
    }

    /*private fun getUserData() {
        val apiProvider = ApiProvider.getInstance().create(ServerApi::class.java)
        val token = "Bearer " + mainActivity.token.getToken()
        Log.d("token", token)
        apiProvider.getUserInfo(token).enqueue(object : Callback<MyPageData> {
            override fun onResponse(call: Call<MyPageData>, response: Response<MyPageData>) {
                if (response.isSuccessful) {
                    MainActivity().userData = response.body()!!
                    binding.textHomeName.text = response.body()!!.nickname + "님"
                } else {
                    Log.d("server", response.code().toString())
                    Log.d("server", response.message().toString())
                    Toast.makeText(activity, "정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<MyPageData>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    override fun onSchoolClicked(schoolName: String) {
        mainActivity.schoolInfo(schoolName)
    }
}
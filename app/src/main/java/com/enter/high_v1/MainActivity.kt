package com.enter.high_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.enter.high_v1.databinding.ActivityMainBinding
import com.enter.high_v1.home.HomeFragment
import com.enter.high_v1.inspection.InspectionQuestFragment
import com.enter.high_v1.inspection.InspectionResultFragment
import com.enter.high_v1.inspection.InspectionStartFragment
import com.enter.high_v1.mypage.MyPageData
import com.enter.high_v1.mypage.MyPageFragment
import com.enter.high_v1.school.InfoDepartFragment
import com.enter.high_v1.school.InfoSummaryFragment
import com.enter.high_v1.school.SchoolInfoFragment
import com.enter.high_v1.school.SchoolInfoResponse
import com.enter.high_v1.school.SchoolRecommendFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //val token = Token()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        getUserData()

        // token = intent.getStringExtra("token").toString()

        // val accessToken = intent.getStringExtra("token")
        // token.setToken(accessToken.toString())
        setContentView(binding.root)

        replaceHomeFragment()
    }

   fun getUserData() : MyPageData {
        val apiProvider = ApiProvider.getInstance().create(ServerApi::class.java)
        val myApplication = MyApplication
        val token = myApplication.prefs.getPref("token", "")
        val accessToken = "Bearer $token"
       var userData = MyPageData("tjdms", "서은", "tjdms@gmail.com")
        Log.d("token", accessToken)
        apiProvider.getUserInfo(accessToken).enqueue(object : Callback<MyPageData> {
            override fun onResponse(call: Call<MyPageData>, response: Response<MyPageData>) {
                if (response.isSuccessful) {
                    // MainActivity().userData = response.body()!!
                    // binding.textHomeName.text = response.body()!!.nickname + "님"
                    userData = response.body()!!
                    Log.d("user", userData.toString())
                } else {
                    Log.d("server", response.code().toString())
                    Log.d("server", response.message().toString())
                }
            }
            override fun onFailure(call: Call<MyPageData>, t: Throwable) {
                Log.d("server", t.message.toString())
            }
        })
       return userData
    }

    fun replaceHomeFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.lay_main_frame, HomeFragment()).commit()
    }

    fun addFragment(index: Int) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when(index) {
            0 -> transaction.replace(R.id.lay_main_frame, HomeFragment())
            1 -> transaction.replace(R.id.lay_main_frame, InspectionStartFragment())
            2 -> transaction.replace(R.id.lay_main_frame, MyPageFragment())
            3 -> transaction.replace(R.id.lay_main_frame, InspectionQuestFragment())
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun inspectionResult(index: Int, first: String, second: String) {
        val transaction = supportFragmentManager.beginTransaction()
        when(index) {
            0 -> transaction.replace(R.id.lay_main_frame, InspectionResultFragment(first, second))
            1 -> transaction.replace(R.id.lay_main_frame, SchoolRecommendFragment(first, second))
        }
        transaction.commit()
    }

    fun schoolInfo(name: String) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.lay_main_frame, SchoolInfoFragment(name))
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun schoolDetailInfo(index: Int, schoolInfo: SchoolInfoResponse) {
        val transaction = supportFragmentManager.beginTransaction()
        when(index) {
            0 -> transaction.replace(R.id.lay_info_frame, InfoSummaryFragment(schoolInfo))
            1 -> transaction.replace(R.id.lay_info_frame, InfoDepartFragment(schoolInfo))
        }
        transaction.commit()
    }
}
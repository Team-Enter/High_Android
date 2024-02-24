package com.enter.high_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    var userData: MyPageData = MyPageData("user", "푕힁영", "email")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addFragment(0)
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
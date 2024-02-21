package com.enter.high_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.enter.high_v1.databinding.ActivityMainBinding
import com.enter.high_v1.home.HomeFragment
import com.enter.high_v1.inspection.InspectionQuestFragment
import com.enter.high_v1.mypage.MyPageData
import com.enter.high_v1.mypage.MyPageFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userData: MyPageData
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
            1 -> transaction.replace(R.id.lay_main_frame, InspectionQuestFragment())
            2 -> transaction.replace(R.id.lay_main_frame, MyPageFragment())
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
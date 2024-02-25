package com.enter.high_v1.inspection

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enter.high_v1.ApiProvider
import com.enter.high_v1.MainActivity
import com.enter.high_v1.OpenApiProvider
import com.enter.high_v1.OpenServerApi
import com.enter.high_v1.ServerApi
import com.enter.high_v1.databinding.FragmentInspectionQuestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InspectionQuestFragment : Fragment() {
    private lateinit var binding: FragmentInspectionQuestBinding
    var answerList = arrayOfNulls<Int>(67)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInspectionQuestBinding.inflate(layoutInflater, container, false)

        getInspectionList()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerInspectionQ.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = binding.recyclerInspectionQ.layoutManager
                val lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val itemCount = binding.recyclerInspectionQ.adapter?.itemCount ?: 0

                if (lastVisibleItemPosition == itemCount - 1) {
                    binding.btnInspectionQSubmit.visibility = View.VISIBLE
                } else {
                    binding.btnInspectionQSubmit.visibility = View.INVISIBLE
                }

                binding.btnInspectionQSubmit.setOnClickListener {
                    val emptyCheck = answerList.all { it != null }
                    if (emptyCheck) {
                        getScore()
                    } else {
                        Toast.makeText(activity, "검사가 완료되지 않았습니다", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun getInspectionList() {
        val apiProvider = OpenApiProvider.getInstance().create(OpenServerApi::class.java)
        apiProvider.getInspectionQuestion().enqueue(object : Callback<InspectionQuestion> {
            override fun onResponse(call: Call<InspectionQuestion>, response: Response<InspectionQuestion>) {
                if (response.isSuccessful) {
                    val inspectionList = response.body()?.RESULT!!
                    val adapter = InspectionQuestAdapter(inspectionList)
                    binding.recyclerInspectionQ.adapter = adapter
                    binding.recyclerInspectionQ.layoutManager = LinearLayoutManager(activity)
                } else {
                    Log.d("server", response.code().toString())
                    Toast.makeText(activity, "정보를 불러오는데 실패하였습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<InspectionQuestion>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getScore() {
        val score = mutableListOf<Int>()
        for (i in 0..66 step 6) {
            var s = 0
            for(j in i..i + 5) {
                s += answerList[j]!!
            }
            score.add(s)
        }
        var firstIndex = 0
        var secondIndex = 0
        for (i in 1..11) {
            if (score[i] > score[firstIndex]) {
                secondIndex = firstIndex
                firstIndex = i
            } else if(score[i] > score[secondIndex]) {
                secondIndex = i
            }
        }

        MainActivity().inspectionResult(0, aptitude[firstIndex], aptitude[secondIndex])
    }

    private val aptitude = listOf<String>(
        "신체·운동능력",
        "손재능",
        "공간지각력",
        "음악능력",
        "창의력",
        "언어능력",
        "수리·논리력",
        "자기성찰능력",
        "대인관계능력",
        "자연친화력",
        "예술시각능력"
    )
}
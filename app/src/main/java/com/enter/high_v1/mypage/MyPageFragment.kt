package com.enter.high_v1.mypage

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.enter.high_v1.ApiProvider
import com.enter.high_v1.MainActivity
import com.enter.high_v1.ServerApi
import com.enter.high_v1.Token
import com.enter.high_v1.databinding.FragmentMyPageBinding
import com.enter.high_v1.start.StartActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageFragment : Fragment() {
    private lateinit var binding: FragmentMyPageBinding
    private val userData = MainActivity().userData
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserData()

        binding.btnMyLogout.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("로그아웃 하시겠어요?")
                .setMessage("언제든지 다시 로그인하실 수 있어요")
                .setPositiveButton("로그아웃", DialogInterface.OnClickListener {_, _ ->
                    logout()
                })
                .setNegativeButton("다음에", DialogInterface.OnClickListener {_, _ -> })
        }

        binding.btnMyBug.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("죄송합니다.")
                .setMessage("아직 '버그제보'는 기획 중에 있기에 개발되지 않았습니다")
                .setPositiveButton("확인", DialogInterface.OnClickListener {_, _ -> })
        }

        binding.imgMyCancel.setOnClickListener {
            MainActivity().addFragment(0)
        }
    }

    private fun setUserData() {
        binding.textMyName.text = userData.nickname
        binding.textMyMail.text = userData.email
    }

    private fun logout() {
        val token = "Bearer " + Token().getToken()
        val apiProvider = ApiProvider.getInstance().create(ServerApi::class.java)
        apiProvider.logout(token).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity, "로그아웃이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    StartActivity().changeStartFragment(0)
                } else {
                    Log.d("server", response.code().toString())
                    Toast.makeText(activity, "로그아웃에 실패하였습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
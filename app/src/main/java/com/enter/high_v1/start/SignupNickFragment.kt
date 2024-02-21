package com.enter.high_v1.start

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.enter.high_v1.ApiProvider
import com.enter.high_v1.R
import com.enter.high_v1.ServerApi
import com.enter.high_v1.databinding.FragmentSignupNickBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupNickFragment(val accountId: String, val pw: String, val mail: String) : Fragment() {
    private lateinit var binding: FragmentSignupNickBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupNickBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNickSignup.setOnClickListener {
            val nick = binding.editNick.text.toString()
            if (nick.length >= 2) {
                signUp(nick)
            }
        }
    }

    private fun signUp(nick: String) {
        val apiProvider = ApiProvider.getInstance().create(ServerApi::class.java)
        apiProvider.signUp(SignupRequest(accountId, nick, pw, mail)).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    val hostActivity = activity as StartActivity
                    hostActivity.changeStartFragment(0)
                } else {
                    Log.d("server", response.code().toString())
                    Log.d("server", response.message().toString())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
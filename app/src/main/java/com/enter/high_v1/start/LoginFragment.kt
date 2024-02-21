package com.enter.high_v1.start

import android.content.Intent
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
import com.enter.high_v1.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLoginLogin.setOnClickListener {
            val id = binding.editLoginId.text.toString()
            val pw = binding.editLoginPw.text.toString()
            if (id.length >= 5 && pw.length >= 8)
                login(id, pw)
            else
                Toast.makeText(activity, "아이디나 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(id: String, pw: String) {
        val apiProvider = ApiProvider.getInstance().create(ServerApi::class.java)
        apiProvider.login(LoginRequest(id, pw)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful) {
                    Log.d("token", response.body()?.accessToken.toString())
                    val token = response.body()?.accessToken.toString()
                    Token().setToken(token)

                    val intent = Intent(activity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.d("server", response.code().toString())
                    Log.d("server", response.message().toString())
                    Toast.makeText(activity, "아이디나 비밀번호가 맞지 않습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("server", t.message.toString())
                Toast.makeText(activity, "서버 연동에 실패하였습니다", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
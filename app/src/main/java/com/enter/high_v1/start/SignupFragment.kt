package com.enter.high_v1.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enter.high_v1.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSignup.setOnClickListener {
            val mail = binding.editSignupMail.text.toString()
            val id = binding.editSignupId.text.toString()
            val pw = binding.editSignupPw.text.toString()
            if (mail.isNotEmpty() && id.length >= 5 && pw.length >= 8) {
                val hostActivity = activity as StartActivity
                hostActivity.signupNext(id, pw, mail)
            }
        }
    }
}
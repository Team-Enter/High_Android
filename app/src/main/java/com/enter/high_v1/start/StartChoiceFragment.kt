package com.enter.high_v1.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enter.high_v1.databinding.FragmentStartChoiceBinding

class StartChoiceFragment : Fragment() {
    private lateinit var binding: FragmentStartChoiceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartChoiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val hostActivity = activity as StartActivity
        binding.btnStartSignup.setOnClickListener {
            hostActivity.changeStartFragment(1)
            // StartActivity().changeStartFragment(1)
        }
        binding.textStartLogin.setOnClickListener {
            hostActivity.changeStartFragment(2)
            // StartActivity().changeStartFragment(2)
        }
    }
}
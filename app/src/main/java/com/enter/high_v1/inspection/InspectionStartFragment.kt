package com.enter.high_v1.inspection

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enter.high_v1.MainActivity
import com.enter.high_v1.databinding.FragmentInspectionStartBinding

class InspectionStartFragment : Fragment() {
    private lateinit var binding: FragmentInspectionStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInspectionStartBinding.inflate(layoutInflater, container, false)
        tagColor()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnInspectionSStart.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.addFragment(3)
        }
    }

    private fun tagColor() {
        binding.textInspectionSTagSport.background = setTagColor(0)
        binding.textInspectionSTagHand.background = setTagColor(1)
        binding.textInspectionSTagSpace.background = setTagColor(2)
        binding.textInspectionSTagMusic.background = setTagColor(3)
        binding.textInspectionSTagLanguage.background = setTagColor(4)
        binding.textInspectionSTagArt.background = setTagColor(5)
        binding.textInspectionSTagMath.background = setTagColor(6)
        binding.textInspectionSTagSelf.background = setTagColor(7)
        binding.textInspectionSTagHuman.background = setTagColor(8)
        binding.textInspectionSTagNature.background = setTagColor(9)
        binding.textInspectionSTagCreative.background = setTagColor(10)
    }

    private fun setTagColor(index: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(Color.parseColor(tagBackground[index]))
        gradientDrawable.setStroke(3, Color.parseColor(tagStroke[index]))
        gradientDrawable.cornerRadius = 100F
        return gradientDrawable
    }

    private val tagBackground = listOf(
        "#D4F1FE",
        "#F7DFFF",
        "#DFF6C9",
        "#FFE4C4",
        "#F4FBC7",
        "#FFDCDC",
        "#D5F6F2",
        "#E3E2FA",
        "#FFD0D0",
        "#D7F5D5",
        "#DBE1FF"
    )

    private val tagStroke = listOf(
        "#1696F2",
        "#AD58CB",
        "#62B231",
        "#F5841B",
        "#D9C300",
        "#F95083",
        "#18AB91",
        "#7764C1",
        "#D52323",
        "#258B29",
        "#494FE3"
    )
}
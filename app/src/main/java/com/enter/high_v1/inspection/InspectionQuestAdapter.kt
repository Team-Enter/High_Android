package com.enter.high_v1.inspection

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.enter.high_v1.databinding.ItemInspectionRecyclerBinding

class InspectionQuestAdapter(private val inspectionList: List<InspectionQuestListData>) :
    Adapter<InspectionQuestAdapter.InspectionQuestViewHolder>() {
    inner class InspectionQuestViewHolder(private val binding: ItemInspectionRecyclerBinding) :
        ViewHolder(binding.root) {
//        val answer1 = binding.textInspectionQItemAnswer1
//        val answer2 = binding.textInspectionQItemAnswer2
//        val answer3 = binding.textInspectionQItemAnswer3
//        val answer4 = binding.textInspectionQItemAnswer4
//        val answer5 = binding.textInspectionQItemAnswer5
//        val answer6 = binding.textInspectionQItemAnswer6
//        val answer7 = binding.textInspectionQItemAnswer7
         val answerView = listOf(
            binding.textInspectionQItemAnswer1,
            binding.textInspectionQItemAnswer2,
            binding.textInspectionQItemAnswer3,
            binding.textInspectionQItemAnswer4,
            binding.textInspectionQItemAnswer5,
            binding.textInspectionQItemAnswer6,
            binding.textInspectionQItemAnswer7
        )

        private val blue = Color.parseColor("#0080F7")
        private val gray = Color.parseColor("#EFEFEF")

        fun bind(inspectionData: InspectionQuestListData) {
            binding.textInspectionQItemNumber.text = inspectionData.qitemNo.toString()
            binding.textInspectionQQuestion.text = inspectionData.question
        }

        fun addList(inspectionData: InspectionQuestListData, answer: Int) {
            val inspectionQuestFragment = InspectionQuestFragment()
            inspectionQuestFragment.answerList[inspectionData.qitemNo] = answer
            for (i in 0..6) {
                val gradientDrawable = GradientDrawable()
                gradientDrawable.cornerRadius = 300F
                if (i == answer - 1) {
                    gradientDrawable.setColor(blue)
                } else {
                    gradientDrawable.setColor(gray)
                }
                answerView[i].background = gradientDrawable
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspectionQuestViewHolder {
        val binding = ItemInspectionRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InspectionQuestViewHolder(binding)
    }

    override fun getItemCount(): Int = inspectionList.size

    override fun onBindViewHolder(holder: InspectionQuestViewHolder, position: Int) {
        holder.bind(inspectionList[position])

        holder.answerView.forEachIndexed { index, answerView ->
            answerView.setOnClickListener { holder.addList(inspectionList[position], index + 1) }
        }
    }
}
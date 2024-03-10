package com.enter.high_v1.inspection

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.enter.high_v1.R
import com.enter.high_v1.databinding.ItemInspectionRecyclerBinding

class InspectionQuestAdapter(private val inspectionList: List<InspectionQuestListData>) :
    Adapter<InspectionQuestAdapter.InspectionQuestViewHolder>() {
        private val selectedAnswers = MutableList(inspectionList.size) { -1 }
    inner class InspectionQuestViewHolder(private val binding: ItemInspectionRecyclerBinding) : ViewHolder(binding.root) {

        val answerView = listOf(
            binding.textInspectionQItemAnswer1,
            binding.textInspectionQItemAnswer2,
            binding.textInspectionQItemAnswer3,
            binding.textInspectionQItemAnswer4,
            binding.textInspectionQItemAnswer5,
            binding.textInspectionQItemAnswer6,
            binding.textInspectionQItemAnswer7
        )

        fun bind(inspectionData: InspectionQuestListData) {
            binding.textInspectionQItemNumber.text = inspectionData.qitemNo.toString()
            binding.textInspectionQQuestion.text = inspectionData.question
        }

        fun addList(inspectionData: InspectionQuestListData, answer: Int) {
            if (answer != -1) {
                val inspectionQuestFragment = InspectionQuestFragment()
                inspectionQuestFragment.answerList[inspectionData.qitemNo] = answer + 1
            }
            for (i in 0..6) {
                if (i == answer) {
                    selectedView(answerView[i])
                    Log.d("inspection", "blue $i $answer")
                } else {
                    unselectedView(answerView[i])
                    Log.d("inspection", "gray $i $answer")
                }
            }
            Log.d("inspection", "${inspectionData.qitemNo}")
        }
        private fun selectedView(answerView: TextView) {
            answerView.setBackgroundResource(R.drawable.inspection_answer_after_background)
            answerView.setTextColor(Color.WHITE)
        }
        private fun unselectedView(answerView: TextView) {
            answerView.setBackgroundResource(R.drawable.inspection_answer_before_background)
            answerView.setTextColor(Color.BLACK)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspectionQuestViewHolder {
        val binding = ItemInspectionRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InspectionQuestViewHolder(binding)
    }

    override fun getItemCount(): Int = inspectionList.size

    override fun onBindViewHolder(holder: InspectionQuestViewHolder, position: Int) {
        holder.bind(inspectionList[position])

        if (selectedAnswers[position] != -1) {
            holder.addList(inspectionList[position], selectedAnswers[position])
        } else {
            holder.addList(inspectionList[position], -1)
        }

        holder.answerView.forEachIndexed { index, answerView ->
            answerView.setOnClickListener {
                Log.d("inspection", "position : $position")
                selectedAnswers[position] = index
                holder.addList(inspectionList[position], index)
                notifyDataSetChanged()
            }
        }
    }
}
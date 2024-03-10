package com.enter.high_v1.school

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.enter.high_v1.MainActivity
import com.enter.high_v1.databinding.ItemSchoolRecyclerBinding

class SchoolRecommendAdapter(private val recommendList: List<SchoolRecommendData>) : Adapter<SchoolRecommendAdapter.SchoolRecommendViewHolder>() {
    inner class SchoolRecommendViewHolder(private val binding: ItemSchoolRecyclerBinding) : ViewHolder(binding.root) {
        fun bind(recommendData: SchoolRecommendData) {
            binding.textHomeItemName.text = recommendData.name
            binding.textHomeItemType.text = recommendData.Stype
            binding.textHomeItemPlace.text = recommendData.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolRecommendViewHolder {
        val binding = ItemSchoolRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolRecommendViewHolder(binding)
    }

    override fun getItemCount(): Int = recommendList.size

    override fun onBindViewHolder(holder: SchoolRecommendViewHolder, position: Int) {
        holder.bind(recommendList[position])

        holder.itemView.setOnClickListener {
            val recommendData = recommendList[position]
            MainActivity().schoolInfo(recommendData.name)
        }
    }
}
package com.enter.high_v1.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.enter.high_v1.databinding.ItemHomeRecyclerBinding

class HomeAdapter(private val homeList: List<HomeListData>) : Adapter<HomeAdapter.HomeViewHolder>() {
    inner class HomeViewHolder(private val binding: ItemHomeRecyclerBinding) : ViewHolder(binding.root) {
        fun bind(homeData: HomeListData) {
            binding.textHomeItemName.text = homeData.schoolName
            binding.textHomeItemPlace.text = homeData.place
            binding.textHomeItemType.text = homeData.schoolType
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemHomeRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = homeList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(homeList[position])
    }
}
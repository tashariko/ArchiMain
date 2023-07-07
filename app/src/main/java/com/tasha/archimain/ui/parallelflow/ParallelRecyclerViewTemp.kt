package com.tasha.archimain.ui.parallelflow

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.databinding.ListItemTrendingGridBinding
import java.util.*

class ParallelRecyclerViewTemp : RecyclerView.Adapter<ParallelRecyclerViewTemp.ParallelViewHolder>() {

    private var data: List<TrendingItem> = ArrayList()
    private lateinit var binding: ListItemTrendingGridBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = ParallelViewHolder(ListItemTrendingGridBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ParallelViewHolder, position: Int) = holder.bind(data[position])

    fun swapData(data: List<TrendingItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ParallelViewHolder(val binding: ListItemTrendingGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendingItem) = with(binding) {

        }
    }
}
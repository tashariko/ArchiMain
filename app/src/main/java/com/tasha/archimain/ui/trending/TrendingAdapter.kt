package com.tasha.archimain.ui.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tasha.archimain.data.source.local.entity.TrendingItem
import com.tasha.archimain.databinding.ListItemTrendingBinding

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {
    private var dataSet = ArrayList<TrendingItem>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) = TrendingViewHolder(ListItemTrendingBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false))

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TrendingViewHolder, position: Int) {
        viewHolder.updateView(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
    // Return the size of your dataset (invoked by the layout manager)

    fun updateData(data: List<TrendingItem>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyItemRangeChanged(0,dataSet.size)
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class TrendingViewHolder(private val binding: ListItemTrendingBinding) : RecyclerView.ViewHolder(binding.root) {

        fun updateView(trendingItem: TrendingItem) {
            binding.titleView.text = trendingItem.originalTitle ?: "Title"
            binding.overviewView.text = trendingItem.overview
            binding.imageView.load(trendingItem.posterPath)
        }

    }

}

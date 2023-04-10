package com.tasha.archimain.ui.trending

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tasha.archimain.R
import com.tasha.archimain.data.source.local.entity.TrendingItem

class TrendingAdapter : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {
    private var dataSet = ArrayList<TrendingItem>()
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class TrendingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun updateView(trendingItem: TrendingItem) {
            textView.text = trendingItem.originalTitle ?: "Title"
            overview.text = trendingItem.overview
            imageView.load(trendingItem.posterPath)
        }

        val textView: TextView
        val overview:TextView
        val imageView: ImageView

        init {
            textView = view.findViewById(R.id.titleView)
            overview = view.findViewById(R.id.overviewView)
            imageView = view.findViewById(R.id.imageView)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TrendingViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_trending, viewGroup, false)
        return TrendingViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TrendingViewHolder, position: Int) {
        viewHolder.updateView(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    fun updateData(data: java.util.ArrayList<TrendingItem>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyItemRangeChanged(0,dataSet.size)
    }

}

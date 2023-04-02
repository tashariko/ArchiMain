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

class TrendingAdapter(private val dataSet: ArrayList<TrendingItem>) : RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class TrendingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView
        val titleView: TextView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.overviewView)
            titleView = view.findViewById(R.id.titleView)
            imageView = view.findViewById(R.id.imageView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TrendingViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_trending, viewGroup, false)

        return TrendingViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: TrendingViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].originalTitle
        viewHolder.titleView.text = dataSet[position].overview
        viewHolder.imageView.load(dataSet[position].posterPath)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return dataSet.size
    }
    fun addData(data: List<TrendingItem>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }

}

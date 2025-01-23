package com.usman.effectmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.usman.effectmvvm.utils.FilterModel

class FilterAdapter(
    private val filterList: List<FilterModel>,
    private val clicklistener: (FilterModel) -> Unit
) :
    RecyclerView.Adapter<FilterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_filter_view, parent, false)
        return FilterViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        val filter = filterList[position]

        holder.filterName.text = filter.name.toString()
        holder.filterImage.setImageResource(filter.imageResId)
        holder.itemView.setOnClickListener { clicklistener(filter) }

    }

    override fun getItemCount(): Int {
        return filterList.size
    }


}

class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val filterImage: ImageView = itemView.findViewById(R.id.imgFilterView)
    val filterName: TextView = itemView.findViewById(R.id.txtFilterName)
}
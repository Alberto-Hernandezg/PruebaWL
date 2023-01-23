package com.example.pruebawl.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebawl.databinding.PoiRowItemBinding
import com.example.pruebawl.model.Poi

class PoiAdapter : ListAdapter<Poi, PoiAdapter.PoiViewHolder>(DIFF_CALLBACK) {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoiViewHolder {
        val binding = PoiRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PoiViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: PoiViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PoiViewHolder(private val binding: PoiRowItemBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                listener.onItemClick(bindingAdapterPosition)
            }
        }
        fun bind(poi: Poi) {
            binding.poi = poi
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Poi>() {
            override fun areItemsTheSame(oldItem: Poi, newItem: Poi): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Poi, newItem: Poi): Boolean {
                return oldItem == newItem
            }
        }
    }
}
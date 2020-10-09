package com.jesusbadenas.oompaloompascrew.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.databinding.ItemOlBinding

class OLAdapter : ListAdapter<OompaLoompa, OLAdapter.OLViewHolder>(OLDiffCallback()), Filterable {

    interface OnItemClickListener {
        fun onItemClicked(id: Int)
    }

    private val filter = OLFilter(this)
    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OLViewHolder {
        val binding = DataBindingUtil.inflate<ItemOlBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_ol,
            parent,
            false
        )
        return OLViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OLViewHolder, position: Int) {
        val ol: OompaLoompa = getItem(position)
        holder.bind(ol)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClicked(ol.id)
        }
    }

    override fun getFilter(): OLFilter = filter

    override fun getItemId(position: Int): Long = getItem(position).id.toLong()

    class OLViewHolder(private val binding: ItemOlBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(ol: OompaLoompa) = with(itemView) {
            binding.ol = ol
            binding.executePendingBindings()
        }
    }

    internal class OLDiffCallback : DiffUtil.ItemCallback<OompaLoompa>() {
        override fun areItemsTheSame(oldItem: OompaLoompa, newItem: OompaLoompa) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: OompaLoompa, newItem: OompaLoompa) =
            oldItem.id == newItem.id &&
                    oldItem.firstName == newItem.firstName &&
                    oldItem.lastName == newItem.lastName &&
                    oldItem.profession == newItem.profession &&
                    oldItem.image == newItem.image &&
                    oldItem.gender == newItem.gender
    }
}

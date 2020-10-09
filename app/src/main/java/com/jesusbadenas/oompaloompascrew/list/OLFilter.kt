package com.jesusbadenas.oompaloompascrew.list

import android.widget.Filter
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import java.util.Locale

open class OLFilter(private val adapter: OLAdapter) : Filter() {

    var listToFilter = mutableListOf<OompaLoompa>()

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterResults = FilterResults()
        val text = constraint?.toString()?.toLowerCase(Locale.getDefault())?.trim()

        if (text.isNullOrEmpty()) {
            filterResults.values = listToFilter
        } else {
            filterResults.values = listToFilter.filter {
                it.firstName.toLowerCase(Locale.getDefault()).contains(text)
                        || it.lastName.toLowerCase(Locale.getDefault()).contains(text)
            }.toMutableList()
        }

        return filterResults
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter.submitList(results?.values as MutableList<OompaLoompa>)
    }
}

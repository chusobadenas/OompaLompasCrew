package com.jesusbadenas.oompaloompascrew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.domain.repositories.OompaLoompaRepository
import com.jesusbadenas.oompaloompascrew.util.addMoreItems
import kotlinx.coroutines.launch

class ListViewModel(private val repository: OompaLoompaRepository) : ViewModel() {

    private var currentPage = FIRST_PAGE
    var isLoading = false
    val list = MutableLiveData<MutableList<OompaLoompa>>()

    init {
        loadFirst()
    }

    private fun loadCurrentPage() {
        isLoading = true
        viewModelScope.launch {
            val result = repository.getOompaLoompas(currentPage)
            list.addMoreItems(result)
            isLoading = false
        }
    }

    fun loadFirst() {
        list.value?.clear()
        currentPage = FIRST_PAGE
        loadCurrentPage()
    }

    fun loadMoreItems() {
        currentPage++
        loadCurrentPage()
    }

    companion object {
        private const val FIRST_PAGE = 1
        const val PAGE_SIZE = 25
        const val MAX_SIZE = PAGE_SIZE * PAGE_SIZE
    }
}

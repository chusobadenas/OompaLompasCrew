package com.jesusbadenas.oompaloompascrew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.domain.repositories.OompaLoompaRepository
import kotlinx.coroutines.launch

class ListViewModel(private val olRepository: OompaLoompaRepository) : ViewModel() {

    val list = MutableLiveData<List<OompaLoompa>>()

    init {
        loadOlList()
    }

    fun loadOlList() {
        viewModelScope.launch {
            list.value = olRepository.getOompaLoompas(1)
        }
    }
}

package com.jesusbadenas.oompaloompascrew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.domain.repositories.OompaLoompaRepository
import kotlinx.coroutines.launch

class ListViewModel(private val repository: OompaLoompaRepository) : ViewModel() {

    val list = MutableLiveData<List<OompaLoompa>>()

    init {
        loadOLList()
    }

    fun loadOLList() {
        viewModelScope.launch {
            list.value = repository.getOompaLoompas(1)
        }
    }
}

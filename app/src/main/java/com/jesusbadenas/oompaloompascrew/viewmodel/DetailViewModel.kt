package com.jesusbadenas.oompaloompascrew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompaDetail
import com.jesusbadenas.oompaloompascrew.domain.repositories.OompaLoompaRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Int,
    private val repository: OompaLoompaRepository
) : ViewModel() {

    val olDetail = MutableLiveData<OompaLoompaDetail>()

    init {
        loadOLDetail()
    }

    fun loadOLDetail() {
        viewModelScope.launch {
            olDetail.value = repository.getOompaLoompaById(id)
        }
    }
}

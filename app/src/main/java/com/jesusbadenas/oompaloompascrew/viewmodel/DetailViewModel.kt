package com.jesusbadenas.oompaloompascrew.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompaDetail
import com.jesusbadenas.oompaloompascrew.domain.repositories.OompaLoompaRepository

class DetailViewModel(
    private val id: Int,
    private val repository: OompaLoompaRepository
) : BaseViewModel() {

    val olDetail = MutableLiveData<OompaLoompaDetail>()

    init {
        loadOLDetail()
    }

    fun loadOLDetail() {
        viewModelScope.safeLaunch {
            olDetail.value = repository.getOompaLoompaById(id)
        }
    }
}

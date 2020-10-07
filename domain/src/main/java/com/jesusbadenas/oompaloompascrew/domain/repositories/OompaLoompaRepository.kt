package com.jesusbadenas.oompaloompascrew.domain.repositories

import com.jesusbadenas.oompaloompascrew.data.api.APIService
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompaDetail
import com.jesusbadenas.oompaloompascrew.data.util.toOompaLoompa
import com.jesusbadenas.oompaloompascrew.data.util.toOompaLoompaDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OompaLoompaRepository(private val apiService: APIService) {

    suspend fun getOompaLoompas(page: Int): List<OompaLoompa> =
        withContext(Dispatchers.IO) {
            apiService.getOompaLoompas(page).results.map { ol ->
                ol.toOompaLoompa()
            }
        }

    suspend fun getOompaLoompaById(id: Int): OompaLoompaDetail =
        withContext(Dispatchers.IO) {
            apiService.getOompaLoompaById(id).toOompaLoompaDetail()
        }
}

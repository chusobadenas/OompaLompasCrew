package com.jesusbadenas.oompaloompascrew.data.api

import com.jesusbadenas.oompaloompascrew.data.entities.response.OompaLoompaData
import com.jesusbadenas.oompaloompascrew.data.entities.response.OompaLoompasResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("/napptilus/oompa-loompas")
    suspend fun getOompaLoompas(@Query(PAGE) page: Int): OompaLoompasResponse

    @GET("/napptilus/oompa-loompas/{$ID}")
    suspend fun getOompaLoompaById(@Path(ID) id: Int): OompaLoompaData

    companion object {
        private const val ID = "id"
        private const val PAGE = "page"
        const val BASE_URL = "https://2q2woep105.execute-api.eu-west-1.amazonaws.com/"
    }
}

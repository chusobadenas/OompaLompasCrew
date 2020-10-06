package com.jesusbadenas.oompaloompascrew.di

import com.jesusbadenas.oompaloompascrew.data.di.dataModule
import com.jesusbadenas.oompaloompascrew.domain.di.domainModule

val appComponent = listOf(
    appModule,
    domainModule,
    dataModule
)

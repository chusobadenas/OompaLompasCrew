package com.jesusbadenas.oompaloompascrew.domain.di

import com.jesusbadenas.oompaloompascrew.domain.repositories.OompaLoompaRepository
import org.koin.dsl.module

val domainModule = module {
    factory { OompaLoompaRepository(get()) }
}

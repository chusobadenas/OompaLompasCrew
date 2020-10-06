package com.jesusbadenas.oompaloompascrew.di

import com.jesusbadenas.oompaloompascrew.main.MainFragment
import com.jesusbadenas.oompaloompascrew.viewmodel.MainViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    fragment { MainFragment() }
    viewModel { MainViewModel() }
}

package com.jesusbadenas.oompaloompascrew.di

import androidx.recyclerview.widget.LinearLayoutManager
import com.jesusbadenas.oompaloompascrew.detail.DetailFragment
import com.jesusbadenas.oompaloompascrew.list.ListFragment
import com.jesusbadenas.oompaloompascrew.list.OLAdapter
import com.jesusbadenas.oompaloompascrew.navigation.Navigator
import com.jesusbadenas.oompaloompascrew.viewmodel.DetailViewModel
import com.jesusbadenas.oompaloompascrew.viewmodel.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    fragment { DetailFragment() }
    fragment { ListFragment() }
    factory { LinearLayoutManager(androidContext()) }
    factory { OLAdapter() }
    single { Navigator() }
    viewModel { (id: Int) -> DetailViewModel(id, get()) }
    viewModel { ListViewModel(get()) }
}

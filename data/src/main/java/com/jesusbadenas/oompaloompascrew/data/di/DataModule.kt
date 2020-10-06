package com.jesusbadenas.oompaloompascrew.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jesusbadenas.oompaloompascrew.data.BuildConfig
import com.jesusbadenas.oompaloompascrew.data.api.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }
    factory<Gson> { GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create() }
    factory { provideOkHttpClient(get()) }
    factory { provideAPIService(get()) }
    single { provideRetrofit(get(), get()) }
}

private fun provideOkHttpClient(logInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder().apply {
        // Enable logging
        if (BuildConfig.DEBUG) {
            addInterceptor(logInterceptor)
        }
    }.build()

private fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
    Retrofit.Builder().apply {
        baseUrl(APIService.BASE_URL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create(gson))
    }.build()

private fun provideAPIService(retrofit: Retrofit): APIService =
    retrofit.create(APIService::class.java)

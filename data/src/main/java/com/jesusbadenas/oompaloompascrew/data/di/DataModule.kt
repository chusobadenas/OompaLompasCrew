package com.jesusbadenas.oompaloompascrew.data.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jesusbadenas.oompaloompascrew.data.BuildConfig
import com.jesusbadenas.oompaloompascrew.data.api.APIService
import com.jesusbadenas.oompaloompascrew.data.api.hasNetwork
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val CACHE_CONTROL = "Cache-Control"
private const val CACHE_INTERCEPTOR = "cacheInterceptor"
private const val CACHE_MAX_AGE_SEC = 5
private const val CACHE_MAX_STALE_SEC = 7 * 24 * 60 * 60
private const val CACHE_SIZE_MB: Long = 5 * 1024 * 1024
private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"

val dataModule = module {
    factory(StringQualifier(CACHE_INTERCEPTOR)) {
        provideCacheInterceptor(androidContext())
    }
    factory {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    }
    factory<Gson> {
        GsonBuilder().setDateFormat(DATE_FORMAT).create()
    }
    factory {
        provideOkHttpClient(
            androidContext(),
            get(StringQualifier(CACHE_INTERCEPTOR)),
            get()
        )
    }
    factory { provideAPIService(get()) }
    single { provideRetrofit(get(), get()) }
}

private fun provideCacheInterceptor(context: Context): Interceptor =
    Interceptor { chain ->
        val request = chain.request().newBuilder().apply {
            if (hasNetwork(context)) {
                header(CACHE_CONTROL, "public, max-age=$CACHE_MAX_AGE_SEC")
            } else {
                header(CACHE_CONTROL, "public, max-stale=$CACHE_MAX_STALE_SEC")
            }
        }.build()
        chain.proceed(request)
    }

private fun provideOkHttpClient(
    context: Context,
    cacheInterceptor: Interceptor,
    logInterceptor: HttpLoggingInterceptor
): OkHttpClient =
    OkHttpClient.Builder().apply {
        // Enable cache
        val myCache = Cache(context.cacheDir, CACHE_SIZE_MB)
        cache(myCache)
        addInterceptor(cacheInterceptor)
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

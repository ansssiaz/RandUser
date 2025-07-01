package com.ansssiaz.randuser.di

import com.ansssiaz.randuser.BuildConfig
import com.ansssiaz.randuser.data.api.UsersApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor

private val contentType = "application/json".toMediaType()
private val json = Json { ignoreUnknownKeys = true }

private fun provideOkHttpClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    })
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://randomuser.me/api/")
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()

private fun provideUsersApi(retrofit: Retrofit): UsersApi =
    retrofit.create(UsersApi::class.java)

val apiModule = module {
    single { provideOkHttpClient() }
    single { provideUsersApi(get()) }
    single { provideRetrofit(get()) }
}
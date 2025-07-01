package com.ansssiaz.randuser.data.api

import com.ansssiaz.randuser.data.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET(".")
    suspend fun getUsers(@Query("results") results: Int): UsersResponse
}
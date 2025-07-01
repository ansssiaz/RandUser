package com.ansssiaz.randuser.domain

import com.ansssiaz.randuser.data.model.User

interface UsersRepository {
    suspend fun getUsers(number: Int): List<User>
}
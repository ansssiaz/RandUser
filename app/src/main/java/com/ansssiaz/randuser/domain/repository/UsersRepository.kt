package com.ansssiaz.randuser.domain.repository

import com.ansssiaz.randuser.domain.model.User

interface UsersRepository {
    suspend fun getUsers(number: Int): List<User>
}
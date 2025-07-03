package com.ansssiaz.randuser.data.repository

import com.ansssiaz.randuser.data.api.UsersApi
import com.ansssiaz.randuser.data.model.toUser
import com.ansssiaz.randuser.domain.model.User
import com.ansssiaz.randuser.domain.repository.UsersRepository

class UsersRepositoryImpl(private val api: UsersApi) : UsersRepository {
    override suspend fun getUsers(number: Int): List<User> =
        api.getUsers(number)
            .results
            .map { it.toUser() }
}
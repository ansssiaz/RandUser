package com.ansssiaz.randuser.di

import com.ansssiaz.randuser.data.repository.UsersRepositoryImpl
import com.ansssiaz.randuser.domain.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideUsersRepository(get()) }

    single<UsersRepository> {
        return@single UsersRepositoryImpl(get())
    }
}

private fun provideUsersRepository(repository: UsersRepositoryImpl): UsersRepository = repository
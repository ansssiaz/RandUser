package com.ansssiaz.randuser.di

import com.ansssiaz.randuser.presentation.viewmodel.UsersViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        UsersViewModel(get())
    }
}
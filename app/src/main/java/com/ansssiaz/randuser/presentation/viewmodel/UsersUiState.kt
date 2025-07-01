package com.ansssiaz.randuser.presentation.viewmodel

import com.ansssiaz.randuser.data.model.User
import com.ansssiaz.randuser.util.Status

data class UsersUiState(
    val users: List<User>? = null,
    val status: Status = Status.Idle
) {
    val isEmptyLoading: Boolean = status == Status.Loading && users.isNullOrEmpty()
    val isError: Boolean
        get() = status is Status.Error && users.isNullOrEmpty()
    val isRefreshing: Boolean = status == Status.Refreshing && !users.isNullOrEmpty()
}
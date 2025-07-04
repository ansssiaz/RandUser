package com.ansssiaz.randuser.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.randuser.domain.model.User
import com.ansssiaz.randuser.domain.repository.UsersRepository
import com.ansssiaz.randuser.util.Status
import com.ansssiaz.randuser.util.USERS_COUNT
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(
    private val repository: UsersRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UsersUiState())
    val state = _state.asStateFlow()

    init {
        getUsers(USERS_COUNT)
    }

    fun getUsers(number: Int, isRefresh: Boolean = false) {
        _state.update {
            it.copy(
                status = if (isRefresh) Status.Refreshing else Status.Loading
            )
        }
        viewModelScope.launch {
            try {
                val users = repository.getUsers(number)
                _state.update {
                    it.copy(
                        users = users,
                        selectedUser = null,
                        status = Status.Idle
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(status = Status.Error(e))
                }
            }
        }
    }

    fun setSelectedUser(user: User) {
        _state.update {
            it.copy(
                selectedUser = user
            )
        }
    }

    fun resetSelectedUser() {
        _state.update {
            it.copy(
                selectedUser = null
            )
        }
    }
}
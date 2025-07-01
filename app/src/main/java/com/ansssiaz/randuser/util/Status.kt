package com.ansssiaz.randuser.util

import android.content.Context
import com.ansssiaz.randuser.R
import retrofit2.HttpException
import java.io.IOException

sealed interface Status {
    val throwableOrNull: Throwable?
        get() = (this as? Error)?.throwable

    data object Idle : Status
    data object Loading : Status
    data object Refreshing : Status
    data class Error(val throwable: Throwable) : Status
}

fun Throwable.getErrorText(context: Context): String = when (this) {
    is IOException -> context.getString(R.string.network_error)
    is HttpException -> context.getString(R.string.server_error)
    else -> context.getString(R.string.unknown_error)
}
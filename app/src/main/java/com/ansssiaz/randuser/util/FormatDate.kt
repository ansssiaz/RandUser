package com.ansssiaz.randuser.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun formatDate(dateString: String): String {
    val zonedDateTime = ZonedDateTime.parse(dateString)
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return zonedDateTime.format(formatter)
}
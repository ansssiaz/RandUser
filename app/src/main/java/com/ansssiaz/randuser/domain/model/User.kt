package com.ansssiaz.randuser.domain.model

import com.ansssiaz.randuser.data.model.Coordinates

data class User (
    val lastname: String,
    val name: String,
    val gender: String,
    val dateOfBirth: String,
    val age: Int,
    val email: String,
    val country: String,
    val street: String,
    val houseNumber: Int,
    val city: String,
    val state: String,
    val coordinates: Coordinates,
    val dateOfRegistration: String,
    val phone: String,
    val picture: String
)
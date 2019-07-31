package com.rezaco.core.controller.models.requests

data class CreatePersonRequest (
    val phone: String
)

data class UpdatePersonRequest (
    val email: String,
    val name: String
)
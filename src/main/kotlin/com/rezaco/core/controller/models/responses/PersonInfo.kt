package com.rezaco.core.controller.models.responses

import com.rezaco.core.models.Person
import java.util.*

data class PersonInfo(
    val id: Long,
    val name: String,
    val phone: String,
    val email: String,
    val isActive: Boolean,
    val isDeleted: Boolean,
    val isBlocked: Boolean
) {

    companion object {
        fun convertEntityToPersonInfo(entity: Person) = PersonInfo(
            id = entity.id,
            name = entity.name ?: "...",
            phone = entity.phone,
            email = entity.email,
            isActive = entity.isActive,
            isDeleted = entity.isDeleted,
            isBlocked = entity.isBlocked
        )
    }
}

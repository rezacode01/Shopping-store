package com.rezaco.core.models

import javax.persistence.*

@Entity
@Table(name="person")
class Person(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(
        nullable = false,
        name = "person_id",
        unique = true
    )
    var id: Long = 0,

    @field:Column(
        name = "name",
        nullable = true
    )
    var name: String? = null,

    @field:Column(
        name = "email",
        nullable = false,
        unique = true
    )
    var email: String = "example@email.com",

    @field:Column(
        name = "phone",
        nullable = false,
        unique = true
    )
    var phone: String,

    @field:Column(
        name = "is_active",
        nullable = false
    )
    var isActive: Boolean = false,

    @field:Column(
        name = "is_deleted",
        nullable = false
    )
    var isDeleted: Boolean = false,

    @field:Column(
        name = "is_blocked",
        nullable = false
    )
    var isBlocked: Boolean= false
)
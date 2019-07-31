package com.rezaco.core.models

import javax.persistence.*

@Entity
@Table(name = "product_base")
data class ProductBase(

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(
        name = "product_id",
        nullable = false,
        unique = true
    )
    var productId: Long = 0,

    @field:Column(
        name = "title"
    )
    var title: String = "",

    @field:Column(
        name = "barcode"
    )
    var barcode: String = "",

    @field:Column(
        name = "price"
    )
    var price: Long = 0
)
package com.rezaco.core.services.models

data class CustomPage<T>(
    val list: Iterable<T>,
    val totalElements: Long,
    val totalPages: Int,
    val pageNumber: Int,
    val pageSize: Int
)
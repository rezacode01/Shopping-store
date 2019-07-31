package com.rezaco.core.repositories

import com.rezaco.core.models.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepo : JpaRepository<Person, Long> {
}
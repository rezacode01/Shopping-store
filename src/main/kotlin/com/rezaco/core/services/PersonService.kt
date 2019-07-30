package com.rezaco.core.services

import com.rezaco.core.controller.models.requests.CreateOrUpdatePerson
import com.rezaco.core.controller.models.responses.PersonInfo
import com.rezaco.core.models.Person
import com.rezaco.core.services.models.Page

interface PersonService {
    fun createPerson(request: CreateOrUpdatePerson) : PersonInfo
    fun getAllPerson(page: Int, size: Int) : Page<PersonInfo>
    fun getPersonById(personId: Long) : PersonInfo
}
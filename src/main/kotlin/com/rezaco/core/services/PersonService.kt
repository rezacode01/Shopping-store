package com.rezaco.core.services

import com.rezaco.core.controller.models.requests.CreateOrUpdatePerson
import com.rezaco.core.controller.models.responses.PersonInfo

interface PersonService {
    fun createPerson(request: CreateOrUpdatePerson) : PersonInfo
}
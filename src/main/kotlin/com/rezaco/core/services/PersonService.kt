package com.rezaco.core.services

import com.rezaco.core.controller.models.requests.CreatePersonRequest
import com.rezaco.core.controller.models.requests.UpdatePersonRequest
import com.rezaco.core.controller.models.responses.PersonInfo
import com.rezaco.core.services.models.CustomPage

interface PersonService {
    fun createPerson(request: CreatePersonRequest) : PersonInfo
    fun getAllPerson(page: Int, size: Int) : CustomPage<PersonInfo>
    fun getAllAvailablePerson(page: Int, size: Int) : CustomPage<PersonInfo>
    fun getPersonById(personId: Long) : PersonInfo
    fun blockThisPerson(personId: Long) : PersonInfo
    fun unblockThisPerson(personId: Long) : PersonInfo
    fun updatePersonProfile(personId: Long, request: UpdatePersonRequest) : PersonInfo
}
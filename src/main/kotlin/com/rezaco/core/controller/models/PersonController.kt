package com.rezaco.core.controller.models

import com.rezaco.core.controller.models.requests.CreateOrUpdatePerson
import com.rezaco.core.controller.models.responses.PersonInfo
import com.rezaco.core.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/admin/person")
class PersonController {

    @Autowired
    private lateinit var personService: PersonService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody @Valid request: CreateOrUpdatePerson) : PersonInfo {
        return personService.createPerson(request)
    }
}
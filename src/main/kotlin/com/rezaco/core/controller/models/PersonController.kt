package com.rezaco.core.controller.models

import com.rezaco.core.controller.models.requests.CreateOrUpdatePerson
import com.rezaco.core.controller.models.responses.PersonInfo
import com.rezaco.core.services.PersonService
import com.rezaco.core.services.models.Page
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/admin/persons")
class PersonController {

    @Autowired
    private lateinit var personService: PersonService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPerson(@RequestBody @Valid request: CreateOrUpdatePerson) : PersonInfo {
        return personService.createPerson(request)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllPerson(
        @RequestParam page: Int = 1,
        @RequestParam size: Int = 50
    ) : Page<PersonInfo> {
        return personService.getAllPerson(page, size)
    }

    @GetMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    fun getPersonById(
        @PathVariable personId: Long
    ) : PersonInfo{
       return personService.getPersonById(personId)
    }
}
package com.rezaco.core.controller.models

import com.rezaco.core.controller.models.requests.CreatePersonRequest
import com.rezaco.core.controller.models.requests.UpdatePersonRequest
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
    fun createPerson(@RequestBody @Valid request: CreatePersonRequest) : PersonInfo {
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

    @PostMapping("/{personId}/block")
    @ResponseStatus(HttpStatus.OK)
    fun blockPersonById(@PathVariable personId: Long) : PersonInfo{
        return personService.blockThisPerson(personId)
    }

    @PostMapping("/{personId}/unblock")
    @ResponseStatus(HttpStatus.OK)
    fun unblockPersonById(@PathVariable personId: Long) : PersonInfo{
        return personService.unblockThisPerson(personId)
    }

    @PutMapping("/{personId}")
    @ResponseStatus(HttpStatus.OK)
    fun editPerson(
        @PathVariable personId: Long,
        @RequestBody request: UpdatePersonRequest) : PersonInfo {
            return personService.updatePersonProfile(personId, request)
    }
}
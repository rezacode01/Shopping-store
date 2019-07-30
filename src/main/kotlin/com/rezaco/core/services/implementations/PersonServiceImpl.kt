package com.rezaco.core.services.implementations

import com.rezaco.core.controller.models.requests.CreateOrUpdatePerson
import com.rezaco.core.controller.models.responses.PersonInfo
import com.rezaco.core.enums.CustomErrorType
import com.rezaco.core.exceptions.EntityDoesNotExistException
import com.rezaco.core.models.Person
import com.rezaco.core.repositories.PersonRepo
import com.rezaco.core.services.PersonService
import com.rezaco.core.services.models.Page
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class PersonServiceImpl : PersonService{

    @Autowired
    private lateinit var personRepo: PersonRepo

    private val logger = LoggerFactory.getLogger(PersonServiceImpl::class.java)

    override fun createPerson(request: CreateOrUpdatePerson) : PersonInfo{

        val newPerson = Person(
            email = request.email,
            phone = request.phone
        )

        val savedPersonObject = personRepo.save(newPerson)
        val respPersonInfo = PersonInfo.convertEntityToPersonInfo(savedPersonObject)

        logger.info("New person created: $respPersonInfo")

        return respPersonInfo
    }

    override fun getAllPerson(page: Int, size: Int): Page<PersonInfo> {
        val fetchedPersons = personRepo.findAll(PageRequest.of(page, size))

        return Page(
            list = fetchedPersons.content.map { PersonInfo.convertEntityToPersonInfo(it) },
            totalElements = fetchedPersons.totalElements,
            pageNumber = fetchedPersons.number,
            totalPages = fetchedPersons.totalPages,
            pageSize = fetchedPersons.size
        )
    }

    override fun getPersonById(personId: Long): PersonInfo {
        val fetchedPerson = personRepo.findById(personId)
        if (fetchedPerson.isPresent)
            return PersonInfo.convertEntityToPersonInfo(fetchedPerson.get())
        throw EntityDoesNotExistException(CustomErrorType.PERSON_DOES_NOT_EXIST)
    }

}
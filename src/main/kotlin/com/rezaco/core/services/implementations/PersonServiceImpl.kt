package com.rezaco.core.services.implementations

import com.rezaco.core.controller.models.requests.CreatePersonRequest
import com.rezaco.core.controller.models.requests.UpdatePersonRequest
import com.rezaco.core.controller.models.responses.PersonInfo
import com.rezaco.core.enums.CustomErrorType
import com.rezaco.core.exceptions.EntityDoesNotExistException
import com.rezaco.core.models.Person
import com.rezaco.core.repositories.PersonRepo
import com.rezaco.core.services.PersonService
import com.rezaco.core.services.models.CustomPage
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service


@Service
class PersonServiceImpl : PersonService{

    @Autowired
    private lateinit var personRepo: PersonRepo

    private val logger = LoggerFactory.getLogger(PersonServiceImpl::class.java)

    override fun createPerson(request: CreatePersonRequest) : PersonInfo{

        val newPerson = Person(
            phone = request.phone
        )

        val savedPersonObject = personRepo.save(newPerson)
        val respPersonInfo = PersonInfo.convertEntityToPersonInfo(savedPersonObject)

        logger.info("New person created: $respPersonInfo")

        return respPersonInfo
    }

    override fun getAllPerson(page: Int, size: Int): CustomPage<PersonInfo> {
        val fetchedPersons = personRepo.findAll(PageRequest.of(page, size))

        return CustomPage(
            list = fetchedPersons.content.map { PersonInfo.convertEntityToPersonInfo(it) },
            totalElements = fetchedPersons.totalElements,
            pageNumber = fetchedPersons.number,
            totalPages = fetchedPersons.totalPages,
            pageSize = fetchedPersons.size
        )
    }

    override fun getAllAvailablePerson(page: Int, size: Int): CustomPage<PersonInfo> {
        val fetchedPersons = personRepo.findAll(PageRequest.of(page, size)).filter{ !it.isDeleted }
        val pagedPersons = PageImpl<Person>(fetchedPersons.toList())

        return CustomPage(
            list = pagedPersons.content.map { PersonInfo.convertEntityToPersonInfo(it) },
            totalElements = pagedPersons.totalElements,
            pageNumber = pagedPersons.number,
            totalPages = pagedPersons.totalPages,
            pageSize = pagedPersons.size
        )
    }

    override fun getPersonById(personId: Long): PersonInfo {
        val fetchedPerson = personRepo.findById(personId)
        if (fetchedPerson.isPresent)
            return PersonInfo.convertEntityToPersonInfo(fetchedPerson.get())
        throw EntityDoesNotExistException(CustomErrorType.PERSON_DOES_NOT_EXIST)
    }

    override fun blockThisPerson(personId: Long): PersonInfo {
        val thisPerson = personRepo.findById(personId)
        if (thisPerson.isPresent) {
            thisPerson.get().isBlocked = true
            personRepo.save(thisPerson.get())

            logger.info("Person with id: $personId is blocked")

            return PersonInfo.convertEntityToPersonInfo(thisPerson.get())
        }
        throw EntityDoesNotExistException(CustomErrorType.PERSON_DOES_NOT_EXIST)
    }

    override fun unblockThisPerson(personId: Long): PersonInfo {
        val thisPerson = personRepo.findById(personId)
        if (thisPerson.isPresent) {
            thisPerson.get().isBlocked = false
            personRepo.save(thisPerson.get())

            logger.info("Person with id: $personId is unblocked")

            return PersonInfo.convertEntityToPersonInfo(thisPerson.get())
        }
        throw EntityDoesNotExistException(CustomErrorType.PERSON_DOES_NOT_EXIST)
    }

    override fun updatePersonProfile(personId: Long, request: UpdatePersonRequest): PersonInfo {
        val thisPerson = personRepo.findById(personId)
        if (thisPerson.isPresent) {
            thisPerson.get().name = request.name
            thisPerson.get().email = request.email
            personRepo.save(thisPerson.get())

            logger.info("Person with id: $personId is updated with new info $request")

            return PersonInfo.convertEntityToPersonInfo(thisPerson.get())
        }
        throw EntityDoesNotExistException(CustomErrorType.PERSON_DOES_NOT_EXIST)
    }
}
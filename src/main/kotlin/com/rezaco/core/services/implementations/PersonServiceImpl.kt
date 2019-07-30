package com.rezaco.core.services.implementations

import com.rezaco.core.controller.models.requests.CreateOrUpdatePerson
import com.rezaco.core.controller.models.responses.PersonInfo
import com.rezaco.core.models.Person
import com.rezaco.core.repositories.PersonRepo
import com.rezaco.core.services.PersonService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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

}
package com.rezaco.core.exceptions

import com.rezaco.core.enums.CustomErrorType
import org.apache.logging.log4j.message.Message
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import javax.lang.model.type.ErrorType
import kotlin.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class EntityDoesNotExistException(message: String) : RuntimeException(message) {
    constructor(type: CustomErrorType) : this(type.toString())
}
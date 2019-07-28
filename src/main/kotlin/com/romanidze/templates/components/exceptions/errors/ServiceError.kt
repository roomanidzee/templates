package com.romanidze.templates.components.exceptions.errors

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName

import org.springframework.http.HttpStatus

import java.time.LocalDateTime

@JsonTypeName("service_error")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT ,use = JsonTypeInfo.Id.NAME)
data class ServiceError(

    @JsonProperty("status_code")
    var status: HttpStatus,

    @JsonProperty("error_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
    var errorTime: LocalDateTime,

    @JsonProperty("message")
    var message: String,

    @JsonProperty("debug_message")
    var debugMessage: String? = null

)
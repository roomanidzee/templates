package com.romanidze.templates.components.exceptions.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.romanidze.templates.components.exceptions.errors.ServiceError

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler

import reactor.core.publisher.Mono

import java.time.LocalDateTime
import java.time.ZoneId

@Component
@Order(-2)
class RESTExceptionHandler(private val objectMapper: ObjectMapper): WebExceptionHandler {

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {

        ex.printStackTrace()

        val simpleMessage = """Произошла ошибка при работе с сервисом. Эндпоинт: ${exchange.request.path},
            |адрес: ${exchange.request.remoteAddress}
        """.trimMargin()

        val serviceError = ServiceError(
                status = HttpStatus.BAD_REQUEST,
                errorTime = LocalDateTime.now(ZoneId.of("Europe/Moscow")),
                message = simpleMessage,
                debugMessage = ex.message
        )

        exchange.response.statusCode = serviceError.status
        exchange.response.headers.set("Content-Type", "application/json")
        exchange.response.writeWith(Mono.fromCallable {
            val bufferFactory = exchange.response.bufferFactory()
            bufferFactory.wrap(this.objectMapper.writeValueAsBytes(serviceError))
        })

        return exchange.response.setComplete()

    }

}
package com.romanidze.templates.handlers

import com.romanidze.templates.dto.MessageResponseDTO
import com.romanidze.templates.dto.UserDTO
import com.romanidze.templates.services.interfaces.UserService

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

@Component
class UserHandler(private val userService: UserService) {

    fun all(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok()
                             .body(this.userService.getAllUsers(), UserDTO::class.java)
    }

    fun create(req: ServerRequest): Mono<ServerResponse>{
        return req.bodyToMono(UserDTO::class.java)
                  .flatMap {
                      ServerResponse.ok().body(this.userService.save(it), MessageResponseDTO::class.java)
                  }
    }

}
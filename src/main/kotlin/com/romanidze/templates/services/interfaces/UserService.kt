package com.romanidze.templates.services.interfaces

import com.romanidze.templates.dto.MessageResponseDTO
import com.romanidze.templates.dto.UserDTO

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface UserService {

    fun getAllUsers(): Flux<UserDTO>
    fun save(userDTO: UserDTO): Mono<MessageResponseDTO>

}
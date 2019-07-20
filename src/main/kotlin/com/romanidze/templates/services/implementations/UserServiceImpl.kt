package com.romanidze.templates.services.implementations

import com.romanidze.templates.dto.MessageResponseDTO
import com.romanidze.templates.dto.UserDTO
import com.romanidze.templates.mappers.UserMapper
import com.romanidze.templates.repositories.interfaces.UserRepository
import com.romanidze.templates.services.interfaces.UserService

import org.springframework.stereotype.Service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(private val userMapper: UserMapper,
                      private val userRepository: UserRepository): UserService {
    override fun getAllUsers(): Flux<UserDTO> {
        return this.userRepository.findAll()
                   .map(this.userMapper::domainToDTO)
    }

    override fun save(userDTO: UserDTO): Mono<MessageResponseDTO> {

        val user = this.userMapper.dtoToDomain(userDTO)

        return this.userRepository.save(user)
                   .map { MessageResponseDTO(message="Пользователь сохранён") }
    }
}
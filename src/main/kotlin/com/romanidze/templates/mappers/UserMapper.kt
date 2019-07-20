package com.romanidze.templates.mappers

import com.romanidze.templates.config.mapstruct.MapStructConfig
import com.romanidze.templates.domain.User
import com.romanidze.templates.dto.UserDTO

import org.mapstruct.Mapper

@Mapper(config = MapStructConfig::class)
interface UserMapper {

    fun domainToDTO(user: User): UserDTO
    fun dtoToDomain(userDTO: UserDTO): User

}
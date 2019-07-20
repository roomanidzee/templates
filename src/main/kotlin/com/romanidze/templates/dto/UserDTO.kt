package com.romanidze.templates.dto

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class UserDTO(
        val username: String,
        val password: String
)
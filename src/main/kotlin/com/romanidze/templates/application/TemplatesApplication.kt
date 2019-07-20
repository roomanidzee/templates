package com.romanidze.templates.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@SpringBootApplication
@EnableReactiveMongoRepositories
@EntityScan(basePackages = ["com.romanidze.templates.domain"])
@ComponentScan(basePackages = ["com.romanidze.templates.components",
                               "com.romanidze.templates.config",
                               "com.romanidze.templates.handlers",
                               "com.romanidze.templates.mappers",
                               "com.romanidze.templates.repositories",
                               "com.romanidze.templates.routes",
                               "com.romanidze.templates.services"])
class TemplatesApplication

fun main(args: Array<String>) {
    runApplication<TemplatesApplication>(*args)
}

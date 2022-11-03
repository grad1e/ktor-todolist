package dev.daryl.plugins

import dev.daryl.data.models.ToDoAddModel
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<ToDoAddModel> {
            if (it.title.isNullOrBlank())
                ValidationResult.Invalid("Title should not be blank")
            else
                ValidationResult.Valid
        }
    }
}
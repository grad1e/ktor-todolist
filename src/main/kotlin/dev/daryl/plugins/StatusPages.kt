package dev.daryl.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(status = HttpStatusCode.InternalServerError, message = cause.stackTraceToString())
        }
        exception<RequestValidationException> { call, cause ->
            call.respond(status = HttpStatusCode.BadRequest, message = cause.reasons.joinToString())
        }
    }
}
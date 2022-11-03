package dev.daryl.plugins

import dev.daryl.routes.testRoutes
import dev.daryl.routes.todoRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        todoRoutes()
        testRoutes()
    }
}
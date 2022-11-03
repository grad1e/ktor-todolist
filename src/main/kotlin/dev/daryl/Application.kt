package dev.daryl

import dev.daryl.plugins.*
import dev.daryl.routes.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureLogging()
    configureContentNegotiation()
    configureRouting()
    configureDatabase()
    configureRequestValidation()
    configureStatusPages()
}

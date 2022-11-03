package dev.daryl.plugins

import dev.daryl.data.models.ToDosTable
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    val dbConfig = environment.config.config("database")

    val host = dbConfig.property("host").getString()
    val port = dbConfig.property("port").getString()
    val name = dbConfig.property("name").getString()
    val user = dbConfig.property("user").getString()
    val password = dbConfig.property("password").getString()

    Database.connect(
        url = "jdbc:mysql://$host:$port/$name",
        driver = "com.mysql.cj.jdbc.Driver",
        user = user,
        password = password
    )

    transaction {
        addLogger(StdOutSqlLogger)
        SchemaUtils.createMissingTablesAndColumns(ToDosTable)
    }

}
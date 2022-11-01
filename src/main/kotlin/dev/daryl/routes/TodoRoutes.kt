package dev.daryl.routes

import dev.daryl.data.models.ToDoAdd
import dev.daryl.repository.TodoRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.todoRoutes() {
    val repository = TodoRepositoryImpl()

    route("/todos") {
        get {
            call.respond(repository.getAllTodos())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@get
            }

            val todo = repository.getToDo(id)
            if (todo != null) {
                call.respond(todo)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post {
            val receivedTodo = call.receive<ToDoAdd>()
            val todo = repository.addToDo(receivedTodo)
            call.respond(todo)
        }

        put("/{id}") {
            val receivedTodo = call.receive<ToDoAdd>()
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@put
            }

            val status = repository.updateToDo(
                id = id,
                item = receivedTodo
            )
            if (status) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }

            val status = repository.removeToDo(id)
            if (status) {
                call.respond(HttpStatusCode.OK)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

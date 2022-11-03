package dev.daryl.routes

import dev.daryl.repository.TodoRepositoryImpl
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.testRoutes() {
    val repository = TodoRepositoryImpl()

    post("/test/uploadImage") {

    }

    post("/test/uploadText") {
        val formParams = call.receiveMultipart()

        var title = ""
        var done = ""
        var filePath = ""


        formParams.forEachPart {
            when (it) {
                is PartData.FormItem -> {
                    when (it.name) {
                        "title" -> {
                            title = it.value
                        }

                        "done" -> {
                            done = it.value
                        }
                    }
                }

                is PartData.FileItem -> {
                    val fileName = it.originalFileName
                    val fileBytes = it.streamProvider().readBytes()

                    val uploadsDir = File("uploads")
                    if (!uploadsDir.exists()) {
                        uploadsDir.mkdir()
                    }
                    val file = File(uploadsDir, fileName)
                    filePath = file.path
                    file.writeBytes(fileBytes)
                }

                else -> {}
            }
            it.dispose()
        }

        call.respondText("title=$title done=$done filePath=$filePath")
    }

}

package dev.daryl.data.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

@Serializable
data class ToDoModel(
    val id: Int,
    var title: String,
    var done: Boolean
)

@Serializable
data class ToDoAddModel(
    val title: String?,
    val done: Boolean?
)

object ToDosTable : IntIdTable() {
    val title = varchar(name = "title", length = 1000)
    val done = bool(name = "done")
}

class TodoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TodoEntity>(ToDosTable)

    var title by ToDosTable.title
    var done by ToDosTable.done
}
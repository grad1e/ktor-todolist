package dev.daryl.data.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class ToDo(
    val id: Int,
    var title: String,
    var done: Boolean
)

@Serializable
data class ToDoAdd(
    val title : String,
    val done : Boolean
)

object ToDos : Table() {
    val id = integer(name = "id").autoIncrement()
    val title = varchar(name = "title", length = 1000)
    val done = bool(name = "done")

    override val primaryKey: PrimaryKey
        get() = PrimaryKey(firstColumn = id, name = "PK_ToDo_ID")
}
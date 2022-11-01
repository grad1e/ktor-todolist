package dev.daryl.repository

import dev.daryl.data.models.ToDo
import dev.daryl.data.models.ToDoAdd
import dev.daryl.data.models.ToDos
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class TodoRepositoryImpl : ToDoRepository {

    override fun getAllTodos(): List<ToDo> {
        return transaction {
            ToDos.selectAll().map {
                ToDo(
                    id = it[ToDos.id],
                    title = it[ToDos.title],
                    done = it[ToDos.done]
                )
            }
        }
    }

    override fun getToDo(id: Int): ToDo? {
        return transaction {
            ToDos.select {
                ToDos.id eq id
            }.singleOrNull()?.let {
                ToDo(
                    id = it[ToDos.id],
                    title = it[ToDos.title],
                    done = it[ToDos.done]
                )
            }
        }
    }

    override fun addToDo(item: ToDoAdd): ToDo {
        return transaction {
            ToDos.insert {
                it[title] = item.title
                it[done] = item.done
            }.resultedValues?.singleOrNull()?.let {
                ToDo(
                    id = it[ToDos.id],
                    title = it[ToDos.title],
                    done = it[ToDos.done]
                )
            }!!
        }
    }

    override fun removeToDo(id: Int): Boolean {
        val count = transaction {
            ToDos.deleteWhere {
                ToDos.id eq id
            }
        }
        return count > 0
    }

    override fun updateToDo(id: Int, item: ToDoAdd): Boolean {
        val count = transaction {
            ToDos.update(where = { ToDos.id eq id }) {
                it[title] = item.title
                it[done] = item.done
            }
        }
        return count > 0
    }
}
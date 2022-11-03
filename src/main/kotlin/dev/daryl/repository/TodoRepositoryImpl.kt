package dev.daryl.repository

import dev.daryl.data.models.ToDoModel
import dev.daryl.data.models.ToDoAddModel
import dev.daryl.data.models.ToDosTable
import dev.daryl.data.models.TodoEntity
import org.jetbrains.exposed.dao.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class TodoRepositoryImpl : ToDoRepository {

    override fun getAllTodos(): List<ToDoModel> {
        return transaction {
            TodoEntity.all().map {
                ToDoModel(
                    id = it.id.value,
                    title = it.title,
                    done = it.done
                )
            }
        }
    }

    override fun getToDo(id: Int): ToDoModel? {
        return transaction {
            TodoEntity.find {
                ToDosTable.id eq id
            }.singleOrNull()?.let {
                ToDoModel(
                    id = it.id.value,
                    title = it.title,
                    done = it.done
                )
            }
        }
    }

    override fun addToDo(item: ToDoAddModel): ToDoModel {
        return transaction {
            val addedTodo = TodoEntity.new {
                title = item.title!!
                done = item.done!!
            }
            ToDoModel(
                id = addedTodo.id.value,
                title = addedTodo.title,
                done = addedTodo.done
            )
        }
    }

    override fun removeToDo(id: Int): Boolean {
        return transaction {
            TodoEntity.findById(id)?.delete()
            TodoEntity.findById(id) == null
        }
    }

    override fun updateToDo(id: Int, item: ToDoAddModel): Boolean {
        return transaction {
            val todo = TodoEntity.findById(id)
            todo?.title = item.title!!
            todo?.done = item.done!!
            true
        }
    }
}
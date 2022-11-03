package dev.daryl.repository

import dev.daryl.data.models.ToDoAddModel
import dev.daryl.data.models.ToDoModel

interface ToDoRepository {

    fun getAllTodos(): List<ToDoModel>

    fun getToDo(id: Int): ToDoModel?

    fun addToDo(item: ToDoAddModel): ToDoModel

    fun removeToDo(id: Int): Boolean

    fun updateToDo(id: Int, item: ToDoAddModel): Boolean

}
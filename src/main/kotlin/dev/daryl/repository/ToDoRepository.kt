package dev.daryl.repository

import dev.daryl.data.models.ToDoAdd
import dev.daryl.data.models.ToDo

interface ToDoRepository {

    fun getAllTodos(): List<ToDo>

    fun getToDo(id: Int): ToDo?

    fun addToDo(item: ToDoAdd): ToDo

    fun removeToDo(id: Int): Boolean

    fun updateToDo(id: Int, item: ToDoAdd): Boolean

}
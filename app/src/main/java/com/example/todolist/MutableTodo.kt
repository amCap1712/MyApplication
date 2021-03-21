package com.example.todolist

data class MutableTodo(val todo: Todo, var isCompleted: Boolean = todo.completed == 1L)

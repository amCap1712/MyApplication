package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTodoBinding

class TodoAdapter(
    private val todoItemQueries: TodoItemQueries,
    private val month: Months, private val day: Long
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todos: MutableList<MutableTodo> = mutableListOf()

    init {
        refresh()
    }

    class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mutableTodo: MutableTodo) {
            val isCompleted = mutableTodo.isCompleted
            binding.tvTodoTitle.text = mutableTodo.todo.description
            binding.cbDone.isChecked = isCompleted
            toggleStrikeThrough(isCompleted)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(isChecked)
                mutableTodo.isCompleted = !mutableTodo.isCompleted
            }
        }

        private fun toggleStrikeThrough(isChecked: Boolean) {
            if (isChecked) {
                binding.tvTodoTitle.paintFlags = binding.tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                binding.tvTodoTitle.paintFlags = binding.tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    private fun refresh() {
        val newTodos = todoItemQueries.forDay(month.name, day).executeAsList()
        todos.clear()
        todos.addAll(newTodos.map { MutableTodo(it) })
        notifyDataSetChanged()
    }

    fun addTodo(description: String) {
        todoItemQueries.insertTodo(month.name, day, description)
        refresh()
    }

    fun deleteDoneTodos() {
        todos.filter { it.isCompleted }.forEach { todoItemQueries.deleteTodo(it.todo.id) }
        refresh()
    }

}
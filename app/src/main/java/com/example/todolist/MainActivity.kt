package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var sqlDriver: SqlDriver
    private lateinit var database: Database
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var month: Months
    private var day by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        month = Months.valueOf(((intent.extras?.get("month") ?: "a") as String).toUpperCase())
        day = (intent.extras?.get("day") ?: 1L) as Long

        supportActionBar?.title = month.name.toUpperCase() + " - " + day.toString()

        sqlDriver = AndroidSqliteDriver(Database.Schema, applicationContext, "todo.db")
        database = Database(sqlDriver)
        todoAdapter= TodoAdapter(database.todoItemQueries, month, day)

        binding.rvToDoItems.adapter = todoAdapter
        binding.rvToDoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etToDoTitle.text.toString()
            if(todoTitle.isNotEmpty()) {
                todoAdapter.addTodo(todoTitle)
                binding.etToDoTitle.text.clear()
            }
        }

        binding.btnDeleteDoneTodos.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }
    }
}
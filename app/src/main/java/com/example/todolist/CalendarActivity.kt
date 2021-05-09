package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.todolist.databinding.CalendarBinding
import java.time.LocalDate

class CalendarActivity : AppCompatActivity() {
    private lateinit var binding: CalendarBinding
    private var currentMonth: Months = Months.A
    private var buttons: MutableList<Button> = mutableListOf()
    private var days: MutableList<Pair<Long, LocalDate>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Right.setOnClickListener {
            currentMonth = Months.next(currentMonth)
            binding.Month.text = currentMonth.customName
            updateDays()
        }
        binding.Left.setOnClickListener {
            currentMonth = Months.prev(currentMonth)
            binding.Month.text = currentMonth.customName
            updateDays()
        }

        buttons.apply {
            add(binding.date1)
            add(binding.date2)
            add(binding.date3)
            add(binding.date4)
            add(binding.date5)
            add(binding.date6)
            add(binding.date7)
            add(binding.date8)
            add(binding.date9)
            add(binding.date10)
            add(binding.date11)
            add(binding.date12)
            add(binding.date13)
            add(binding.date14)
            add(binding.date15)
            add(binding.date16)
            add(binding.date17)
            add(binding.date18)
            add(binding.date19)
            add(binding.date20)
            add(binding.date21)
            add(binding.date22)
            add(binding.date23)
            add(binding.date24)
            add(binding.date25)
            add(binding.date26)
            add(binding.date27)
            add(binding.date28)
            add(binding.date29)
            add(binding.date30)
            add(binding.date31)
            add(binding.date32)
            add(binding.date33)
            add(binding.date34)
            add(binding.date35)
            add(binding.date36)
            add(binding.date37)
            add(binding.date38)
        }
        buttons.forEach {
            button -> button.setOnClickListener { openTodos(button.text.toString().toLong()) }
        }
        updateDays()
    }

    private fun openTodos(day: Long) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("month", currentMonth.name)
        intent.putExtra("day", day)
        startActivity(intent)
    }

    private fun updateDays() {
        val numberOfDays = Months.days(currentMonth)
        val startDay = currentMonth.date.dayOfWeek.value

        days.clear()

        for (i in 1..startDay)
            days.add(Pair(-1, LocalDate.MIN))
        for (i in 1..numberOfDays)
            days.add(Pair(i, currentMonth.date.plusDays(i)))
        for (i in days.size..38)
            days.add(Pair(-1, LocalDate.MAX))

        buttons.forEachIndexed { index, button ->
            if (days[index].first == -1L)
                button.visibility = View.GONE
            else {
                button.visibility = View.VISIBLE
                button.text = days[index].second.dayOfMonth.toString()
            }
        }
    }
}
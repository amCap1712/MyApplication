package com.example.todolist

import java.time.LocalDate
import java.time.temporal.ChronoUnit

enum class Months(val customName: String, val date: LocalDate) {
    A("anandita",LocalDate.of(2021, 5, 15)),
    B("nidra",LocalDate.of(2021, 6, 15)),
    C("nvmun-bara", LocalDate.of(2021, 7, 15)),
    D("plot-uttama",LocalDate.of(2021, 8, 15)),
    E("mockstock-jayi",LocalDate.of(2021, 9, 15)),
    F("UN-rupadana",LocalDate.of(2021, 10, 15)),
    G("engdrama-srjana",LocalDate.of(2021, 11, 15)),
    H("citibank-jaya",LocalDate.of(2021, 12, 15)),
    I("sem-anusarana",LocalDate.of(2022, 1, 15)),
    J("raru-barsiki",LocalDate.of(2022, 2, 15)),
    K("dodory",LocalDate.of(2022, 3, 15)),
    L("bhumja",LocalDate.of(2022, 4, 15));

    companion object {
        fun next(months: Months): Months = when (months) {
            A -> B
            B -> C
            C -> D
            D -> E
            E -> F
            F -> G
            G -> H
            H -> I
            I -> J
            J -> K
            K -> L
            L -> A
        }

        fun prev(months: Months): Months = when (months) {
            A -> L
            B -> A
            C -> B
            D -> C
            E -> D
            F -> E
            G -> F
            H -> G
            I -> H
            J -> I
            K -> J
            L -> K
        }

        fun days(months: Months): Long = when (months) {
            L -> ChronoUnit.DAYS.between(L.date.withYear(2021), A.date)
            else -> ChronoUnit.DAYS.between(months.date, next(months).date)
        }
    }

}


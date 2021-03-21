package com.example.todolist

import java.time.LocalDate
import java.time.temporal.ChronoUnit

enum class Months(val date: LocalDate) {
    A(LocalDate.of(2021, 5, 15)),
    B(LocalDate.of(2021, 6, 15)),
    C(LocalDate.of(2021, 7, 15)),
    D(LocalDate.of(2021, 8, 15)),
    E(LocalDate.of(2021, 9, 15)),
    F(LocalDate.of(2021, 10, 15)),
    G(LocalDate.of(2021, 11, 15)),
    H(LocalDate.of(2021, 12, 15)),
    I(LocalDate.of(2022, 1, 15)),
    J(LocalDate.of(2022, 2, 15)),
    K(LocalDate.of(2022, 3, 15)),
    L(LocalDate.of(2022, 4, 15));

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


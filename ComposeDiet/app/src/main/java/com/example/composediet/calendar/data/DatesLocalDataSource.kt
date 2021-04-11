/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.composediet.calendar.data

import com.example.composediet.calendar.model.CalendarDay
import com.example.composediet.calendar.model.CalendarMonth
import com.example.composediet.calendar.model.DayOfWeek
import javax.inject.Inject
import javax.inject.Singleton
typealias CalendarYear = List<CalendarMonth>

/**
 * Annotated with Singleton because [CalendarDay] contains mutable state.
 */
@Singleton
class DatesLocalDataSource @Inject constructor() {

    private val january2020 = CalendarMonth(
        name = "January",
        year = "2020",
        numDays = 31,
        monthNumber = 1,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    private val february2020 = CalendarMonth(
        name = "February",
        year = "2020",
        numDays = 29,
        monthNumber = 2,
        startDayOfWeek = DayOfWeek.Saturday
    )
    private val march2020 = CalendarMonth(
        name = "March",
        year = "2020",
        numDays = 31,
        monthNumber = 3,
        startDayOfWeek = DayOfWeek.Sunday
    )
    private val april2020 = CalendarMonth(
        name = "April",
        year = "2020",
        numDays = 30,
        monthNumber = 4,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    private val may2020 = CalendarMonth(
        name = "May",
        year = "2020",
        numDays = 31,
        monthNumber = 5,
        startDayOfWeek = DayOfWeek.Friday
    )
    private val june2020 = CalendarMonth(
        name = "June",
        year = "2020",
        numDays = 30,
        monthNumber = 6,
        startDayOfWeek = DayOfWeek.Monday
    )
    private val july2020 = CalendarMonth(
        name = "July",
        year = "2020",
        numDays = 31,
        monthNumber = 7,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    private val august2020 = CalendarMonth(
        name = "August",
        year = "2020",
        numDays = 31,
        monthNumber = 8,
        startDayOfWeek = DayOfWeek.Saturday
    )
    private val september2020 = CalendarMonth(
        name = "September",
        year = "2020",
        numDays = 30,
        monthNumber = 9,
        startDayOfWeek = DayOfWeek.Tuesday
    )
    private val october2020 = CalendarMonth(
        name = "October",
        year = "2020",
        numDays = 31,
        monthNumber = 10,
        startDayOfWeek = DayOfWeek.Thursday
    )
    private val november2020 = CalendarMonth(
        name = "November",
        year = "2020",
        numDays = 30,
        monthNumber = 11,
        startDayOfWeek = DayOfWeek.Sunday
    )
    private val december2020 = CalendarMonth(
        name = "December",
        year = "2020",
        numDays = 31,
        monthNumber = 12,
        startDayOfWeek = DayOfWeek.Tuesday
    )

    val year2020: CalendarYear = listOf(
        january2020,
        february2020,
        march2020,
        april2020,
        may2020,
        june2020,
        july2020,
        august2020,
        september2020,
        october2020,
        november2020,
        december2020
    )

    private val january2021 = CalendarMonth(
        name = "January",
        year = "2021",
        numDays = 31,
        monthNumber = 1,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    private val february2021 = CalendarMonth(
        name = "February",
        year = "2021",
        numDays = 28,
        monthNumber = 2,
        startDayOfWeek = DayOfWeek.Saturday
    )
    private val march2021 = CalendarMonth(
        name = "March",
        year = "2021",
        numDays = 31,
        monthNumber = 3,
        startDayOfWeek = DayOfWeek.Sunday
    )
    private val april2021 = CalendarMonth(
        name = "April",
        year = "2021",
        numDays = 30,
        monthNumber = 4,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    private val may2021 = CalendarMonth(
        name = "May",
        year = "2021",
        numDays = 31,
        monthNumber = 5,
        startDayOfWeek = DayOfWeek.Friday
    )
    private val june2021 = CalendarMonth(
        name = "June",
        year = "2021",
        numDays = 30,
        monthNumber = 6,
        startDayOfWeek = DayOfWeek.Monday
    )
    private val july2021 = CalendarMonth(
        name = "July",
        year = "2021",
        numDays = 31,
        monthNumber = 7,
        startDayOfWeek = DayOfWeek.Wednesday
    )
    private val august2021 = CalendarMonth(
        name = "August",
        year = "2021",
        numDays = 31,
        monthNumber = 8,
        startDayOfWeek = DayOfWeek.Saturday
    )
    private val september2021 = CalendarMonth(
        name = "September",
        year = "2021",
        numDays = 30,
        monthNumber = 9,
        startDayOfWeek = DayOfWeek.Tuesday
    )
    private val october2021 = CalendarMonth(
        name = "October",
        year = "2021",
        numDays = 31,
        monthNumber = 10,
        startDayOfWeek = DayOfWeek.Thursday
    )
    private val november2021 = CalendarMonth(
        name = "November",
        year = "2021",
        numDays = 30,
        monthNumber = 11,
        startDayOfWeek = DayOfWeek.Sunday
    )
    private val december2021 = CalendarMonth(
        name = "December",
        year = "2021",
        numDays = 31,
        monthNumber = 12,
        startDayOfWeek = DayOfWeek.Tuesday
    )

    val year2021: CalendarYear = listOf(
        january2021,
        february2021,
        march2021,
        april2021,
        may2021,
        june2021,
        july2021,
        august2021,
        september2021,
        october2021,
        november2021,
        december2021
    )
}

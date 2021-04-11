package com.example.composediet

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.composediet.calendar.CalendarViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.composediet.calendar.Calendar
import com.example.composediet.calendar.model.CalendarDay
import com.example.composediet.calendar.model.CalendarMonth
import com.example.composediet.calendar.data.CalendarYear
import java.time.LocalDate

@Composable
fun CalendarContent(
    selectedDates: String,
    calendarYear: CalendarYear,
    onDayClicked: (CalendarDay, CalendarMonth) -> Unit,
) {
        Column {
            Surface {
                Calendar(calendarYear, onDayClicked)
            }
        }
}

@Composable
fun CalendarScreen(navController: NavHostController, calendarViewModel: CalendarViewModel) {
    val calendarYear= calendarViewModel.calendarYear

    Scaffold(topBar = {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {navController.popBackStack()}) {
                Text(text = "<- Calendar")
            }
        }
    }) {
        CalendarContent(
            selectedDates = calendarViewModel.datesSelected.toString(),
            calendarYear = calendarYear,
            onDayClicked = { calendarDay, calendarMonth ->
                calendarViewModel.onDaySelectedChange(LocalDate.of(calendarMonth.year.toInt(), calendarMonth.monthNumber, calendarDay.value.toInt()))
                navController.navigate(Screen.DayFoodHistory.route)
            }
        )
    }
}
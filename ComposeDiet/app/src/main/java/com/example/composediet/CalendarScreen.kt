package com.example.composediet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composediet.calendar.CalendarViewModel
import com.example.composediet.calendar.model.DaySelected
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.composediet.calendar.Calendar
import com.example.composediet.calendar.model.CalendarDay
import com.example.composediet.calendar.model.CalendarMonth
import com.example.composediet.data.CalendarYear
import java.time.LocalDate
import androidx.compose.runtime.*
import java.time.Month

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
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

package com.example.composediet.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.composediet.data.DatesRepository
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    datesRepository: DatesRepository
) : ViewModel() {

    val datesSelected = datesRepository.datesSelected
    val calendarYear = datesRepository.calendarYear

    private val _daySelected = MutableLiveData(LocalDate.now())
    val daySelected: LiveData<LocalDate> = _daySelected

    fun onDaySelectedChange(localDate: LocalDate) {
        _daySelected.value = localDate
    }
}

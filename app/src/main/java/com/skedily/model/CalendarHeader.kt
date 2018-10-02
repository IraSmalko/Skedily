package com.skedily.model

import org.joda.time.DateTime

/**
 * Created by smalk on 1/25/2018.
 */
class CalendarHeader(
        private val day: DateTime
){
    val isDayOff: Boolean = day.dayOfWeek > 5

    val readableName: String
        get() = day.dayOfWeek().asShortText
}
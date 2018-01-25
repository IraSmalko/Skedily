package com.skedily.utils


import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import org.joda.time.DateTime
import org.joda.time.Interval
import org.joda.time.Period
import org.joda.time.ReadableInterval

/**
 * Created by smalk on 11/26/2017.
 */
fun addFragmentToActivity(manager: FragmentManager, fragment: Fragment, containerId: Int) {
    val transaction = manager.beginTransaction()
    transaction.add(containerId, fragment)
    transaction.commit()
}

fun replaceFragment(manager: FragmentManager, fragment: Fragment, containerId: Int) {
    val transaction = manager.beginTransaction()
    transaction.replace(containerId, fragment)
    transaction.addToBackStack(fragment.javaClass.canonicalName)
    transaction.commit()
}

val DateTime.calendarMonthInterval: Interval
    get() {
        val begin = dayOfMonth().withMinimumValue().dayOfWeek().withMinimumValue()
        val end = dayOfMonth().withMaximumValue().dayOfWeek().withMaximumValue()
        return Interval(begin, end)
    }

val DateTime.calendarWeekInterval: Interval
    get() {
        val begin = dayOfWeek().withMinimumValue()
        val end = dayOfWeek().withMaximumValue()
        return Interval(begin, end)
    }

fun ReadableInterval.days(step: Int = 1): Sequence<DateTime> {
    require(step > 0) { "step must postive value [$step]" }
    return generateSequence(start.startOfDay()) { it + step.days() }.takeWhile { it <= end }
}

fun DateTime.startOfDay(): DateTime = this.withTimeAtStartOfDay()

fun Int.days(): Period = Period.days(this)


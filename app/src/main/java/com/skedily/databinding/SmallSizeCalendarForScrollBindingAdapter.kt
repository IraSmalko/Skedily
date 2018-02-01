package com.skedily.databinding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import java.util.*

/**
 * Created by smalk on 1/25/2018.
 */
class ScheduleScrollListener(private val function: Function0) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {
            // Scroll Down
            function.invoke()
        }
    }
}

private val listeners = WeakHashMap<RecyclerView, ScheduleScrollListener>()

@BindingAdapter("addSmallCalendarListener")
fun addHidingFabListener(rV: RecyclerView, function: Function0) {
    listeners[rV] = ScheduleScrollListener(function)
    listeners[rV]?.let { rV.addOnScrollListener(it) }
}

class CalendarScrollListener(private val function: Function0) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy < 0) {
            // Scroll Up
            function.invoke()
        }
    }
}

private val calendarListeners = WeakHashMap<RecyclerView, CalendarScrollListener>()

@BindingAdapter("scrollCalendarListener")
fun scrollCalendarListener(rV: RecyclerView, function: Function0) {
    calendarListeners[rV] = CalendarScrollListener(function)
    calendarListeners[rV]?.let { rV.addOnScrollListener(it) }
}

interface Function0 {
    fun invoke()
}
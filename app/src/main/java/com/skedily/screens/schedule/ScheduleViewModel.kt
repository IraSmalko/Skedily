package com.skedily.screens.schedule

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.databinding.ItemCalendarHeaderBinding
import com.skedily.databinding.ItemDayBinding
import com.skedily.databinding.ItemScheduleBinding
import com.skedily.model.CalendarHeader
import com.skedily.model.DayItem
import com.skedily.model.Task
import com.skedily.utils.calendarMonthInterval
import com.skedily.utils.calendarWeekInterval
import com.skedily.utils.days
import com.skedily.utils.weak
import org.joda.time.DateTime
import org.joda.time.Interval
import java.lang.IllegalStateException

/**
 * Created by smalk on 1/24/2018.
 */
class ScheduleViewModel : BaseViewModel() {

    var interactor by weak<ScheduleInteractor>()
    val taskItems = mutableListOf<Task>()
    val scheduledTasks = ObservableArrayList<Task>()
    val dayItems = ObservableArrayList<Any>()

    private val today = DateTime.now().withTimeAtStartOfDay()

    var firstDayOfMonth = today.minusDays(today.dayOfMonth - 1)

    var selection = DayItem(today.dayOfMonth)
        @Bindable get
        private set(value) {
            selection.isSelected = false
            value.isSelected = true
            field = value
            notifyPropertyChanged(BR.selection)
        }

    var placeholderVisibility: Boolean = false
        @Bindable get
        private set(value) {
            field = value
            notifyPropertyChanged(BR.placeholderVisibility)
        }

    fun init(list: List<Task>) {
        this.taskItems.addAll(list)
    }

    fun initRecyclers(scheduleRecycler: RecyclerView, calendarRecycler: RecyclerView) {
        taskItems.sortBy { it.startTime }
        LastAdapter(scheduledTasks, BR.item)
                .type { _, _ ->
                    Type<ItemScheduleBinding>(R.layout.item_schedule)
                            .onClick {
                                openTack()
                            }
                }
                .into(scheduleRecycler)
        addHeaders()
        addDays()
        preselectDay()
        loadScheduledOnDayTasks()
        LastAdapter(dayItems, BR.item)
                .type { item, _ ->
                    when (item) {
                        is CalendarHeader -> Type<ItemCalendarHeaderBinding>(R.layout.item_calendar_header)
                        is DayItem -> Type<ItemDayBinding>(R.layout.item_day).onClick {
                            selection = item
                            loadScheduledOnDayTasks()
                        }
                        else -> throw IllegalStateException()
                    }
                }
                .into(calendarRecycler)
    }

    fun addTask() {
        interactor?.openAddCardScreen()
    }

    private fun addHeaders() {
        today.calendarWeekInterval.days().forEach { dayItems += CalendarHeader(it) }
    }

    private fun preselectDay() = dayItems.forEach {
        if (it is DayItem && it.number == today.dayOfMonth) {
            selection = it
        }
    }

    private fun loadScheduledOnDayTasks() {
        scheduledTasks.clear()
        selection.tackList?.forEach {
            scheduledTasks.add(it)
        }
        placeholderVisibility = scheduledTasks.isEmpty()
    }

    private fun addDays() {
        firstDayOfMonth.calendarMonthInterval.days().forEach {
            val item = DayItem(it.dayOfMonth, checkHasTask(it), isThisMonth = it.monthOfYear == today.monthOfYear)
            dayItems += item
        }
    }

    private fun checkHasTask(dateTime: DateTime): List<Task> {
        val taskList = mutableListOf<Task>()
        taskItems.forEach { task ->
            Interval(task.startTime, task.endTime).days().forEach {
                if (it.dayOfYear == dateTime.dayOfYear && it.year == dateTime.year) {
                    taskList.add(task)
                }
            }

        }
        return taskList
    }

    private fun openTack() {

    }
}
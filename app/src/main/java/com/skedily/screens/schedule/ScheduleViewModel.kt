package com.skedily.screens.schedule

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.databinding.ItemDayBinding
import com.skedily.databinding.ItemScheduleBinding
import com.skedily.model.DayItem
import com.skedily.model.Task
import com.skedily.utils.calendarMonthInterval
import com.skedily.utils.days
import com.skedily.utils.weak
import org.joda.time.DateTime
import org.joda.time.Interval

/**
 * Created by smalk on 1/24/2018.
 */
class ScheduleViewModel : BaseViewModel() {

    var interactor by weak<ScheduleInteractor>()
    val taskItems = mutableListOf<Task>()
    val scheduledTasks = ObservableArrayList<Task>()
    val dayItems = ObservableArrayList<DayItem>()

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

        addDays()
        preselectDay()
        LastAdapter(dayItems, BR.item)
                .type { _, _ ->
                    Type<ItemDayBinding>(R.layout.item_day)
                            .onClick {
                                selection = it.binding.item!!
                                loadScheduledOnDayTasks()
                            }
                }
                .into(calendarRecycler)
    }

    fun addTask() {
        interactor?.openAddCardScreen()
    }

    private fun preselectDay() = dayItems.forEach {
        if (it.number == today.dayOfMonth) {
            selection = it
        }
    }

    private fun loadScheduledOnDayTasks() {
        scheduledTasks.clear()
        selection.tackList?.forEach {
            scheduledTasks.add(it)
        }
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
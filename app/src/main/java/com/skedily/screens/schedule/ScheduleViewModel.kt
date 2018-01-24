package com.skedily.screens.schedule

import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.databinding.ItemScheduleBinding
import com.skedily.model.DayItem
import com.skedily.model.Task
import com.skedily.model.User
import com.skedily.utils.days
import com.skedily.utils.weak
import org.joda.time.DateTime
import org.joda.time.Interval

/**
 * Created by smalk on 1/24/2018.
 */
class ScheduleViewModel : BaseViewModel() {

    var interactor by weak<ScheduleInteractor>()
    val taskItems = ObservableArrayList<Task>()
    val dayItems = ObservableArrayList<DayItem>()

    private val today = DateTime.now().withTimeAtStartOfDay()

    var numberOfMonth = today.monthOfYear()

    fun init(list: List<Task>) {
        this.taskItems.addAll(list)
    }

    fun initRecyclers(scheduleRecycler: RecyclerView, calendarRecycler: RecyclerView) {
        taskItems.sortBy { it.startTime }
        LastAdapter(taskItems, BR.item)
                .type { _, _ ->
                    Type<ItemScheduleBinding>(R.layout.item_schedule)
                            .onClick {
                                openTack()
                            }
                }
                .into(scheduleRecycler)

        addDays()
        LastAdapter(dayItems, BR.item)
                .type { _, _ ->
                    Type<ItemScheduleBinding>(R.layout.item_day)
                            .onClick {
                                loadScheduledOnDayTacks()
                            }
                }
                .into(calendarRecycler)
    }

    fun addTack() {
        interactor?.openAddCardScreen()
    }

    private fun loadScheduledOnDayTacks() {

    }

    private fun addDays() {
        val firstShovingDay = numberOfMonth.withMinimumValue().minusDays(numberOfMonth.withMinimumValue().dayOfWeek)
        val lastShovingDay = numberOfMonth.withMaximumValue().plusDays(6 - numberOfMonth.withMaximumValue().dayOfWeek)
        Interval(firstShovingDay, lastShovingDay).days().forEach {
            val item = DayItem(it.dayOfMonth, checkHasTack(it))
            dayItems += item
        }
    }

    private fun checkHasTack(dateTime: DateTime): List<User>? {
        taskItems.forEach { tack ->
            Interval(tack.startTime, tack.endTime).days().forEach {
                if (it.dayOfYear == dateTime.dayOfYear && it.year == dateTime.year) {
                    return tack.listPerson
                }
            }

        }
        return null
    }

    private fun openTack() {

    }
}
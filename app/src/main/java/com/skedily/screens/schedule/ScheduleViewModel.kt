package com.skedily.screens.schedule

import android.location.Address
import android.support.v7.widget.RecyclerView
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.databinding.ItemScheduleBinding
import com.skedily.model.Task
import com.skedily.utils.weak

class ScheduleViewModel : BaseViewModel() {
    var interactor by weak<SheduleInteractor>()
    var taskItems = mutableListOf<Task>()

    fun initRecycler(recycler: RecyclerView) {
        taskItems.sortBy { it.startTime }
        isSoon()
        isFirstOfDay()
        LastAdapter(taskItems, BR.item)
                .type { _, _ ->
                    Type<ItemScheduleBinding>(R.layout.item_schedule)
                            .onClick {

                            }
                }
                .into(recycler)
    }

    private fun isFirstOfDay() {
        taskItems.forEach {
            if (taskItems.size > taskItems.indexOf(it) + 1 && it.startTime.dayOfMonth
                    == taskItems[taskItems.indexOf(it) + 1].startTime.dayOfMonth) {
                taskItems[taskItems.indexOf(it) + 1].firstOfDay = false
            }
        }
    }

    private fun isSoon() {
        taskItems.forEach {
            if (it.startTime.isAfterNow) {
                it.isSoon = true
                return
            }
        }
    }

    fun onClick() {
        interactor!!.onClick()
    }

    fun onClickLocation(address: Address) {
        interactor!!.onClick()
    }
}

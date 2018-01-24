package com.skedily.screens.dashboard

import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.google.android.gms.maps.model.LatLng
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.databinding.ItemScheduleBinding
import com.skedily.model.Task
import com.skedily.utils.weak

class DashboardViewModel : BaseViewModel() {
    var interactor by weak<DashboardInteractor>()
    val taskItems = ObservableArrayList<Task>()

    fun init(list: List<Task>) {
        this.taskItems.addAll(list)
    }

    fun initRecycler(recycler: RecyclerView) {
        taskItems.sortBy { it.startTime }
        LastAdapter(taskItems, BR.item)
                .type { _, _ ->
                    Type<ItemScheduleBinding>(R.layout.item_dashboard)
                            .onClick {

                            }
                }
                .into(recycler)
    }


    fun onClick() {
        interactor?.openTack()
    }

    fun onClickLocation(address: LatLng) {

    }

    fun addTack() {
        interactor?.openAddCardScreen()
    }
}

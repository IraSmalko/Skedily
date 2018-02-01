package com.skedily.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.skedily.BR

/**
 * Created by smalk on 1/24/2018.
 */
data class DayItem(
        val number: Int,
        val tackList: List<Task>? = null,
        val isThisMonth: Boolean = true
) : BaseObservable() {

    var isSelected: Boolean = false
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.selected)
        }

    val userList: List<User>
        get() {
            val list = mutableListOf<User>()
            tackList?.forEach {
                it.listPerson.forEach { list.add(it) }
            }
            return list
        }
}
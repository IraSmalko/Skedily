package com.skedily.screens.add_card

import android.databinding.Bindable
import com.skedily.BR
import com.skedily.base.BaseViewModel

/**
 * Created by smalk on 11/29/2017.
 */
class AddCardViewModel : BaseViewModel() {

    var note: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.note)
        }

    fun getCategory(item: String) {
    }

    fun addChecklist() {

    }

    fun setDay() {

    }

    fun addLocation() {

    }

    fun addFile() {

    }

    fun addPerson() {

    }
}
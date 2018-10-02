package com.skedily.screens.add_card

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.google.android.gms.location.places.Place
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.databinding.ItemChecklistBinding
import com.skedily.model.ChecklistItem
import com.skedily.model.Task
import com.skedily.model.User
import com.skedily.utils.ApiRepository
import com.skedily.utils.weak
import org.joda.time.DateTime

/**
 * Created by smalk on 11/29/2017.
 */
class AddCardViewModel : BaseViewModel() {
    var interactor by weak<AddCardInteractor>()

    private val checklistItems = ObservableArrayList<ChecklistItem>()
    private val listPerson = mutableListOf<User>()
    private lateinit var lastAdapter: LastAdapter

    var note: String = ""
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.note)
        }

    var positionToScroll: Int = 0
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.positionToScroll)
        }

    var pickedPlace: Place? = null
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.pickedPlace)
        }

    var startDay = DateTime()
    var endDay = DateTime()

    var category: String = ""

    fun init(interactor: AddCardInteractor) {
        this.interactor = interactor
    }

    fun initRecycler(recycler: RecyclerView) {
        lastAdapter = LastAdapter(checklistItems, BR.item)
                .type { _, _ ->
                    Type<ItemChecklistBinding>(R.layout.item_checklist)
                            .onClick {
                                val state = it.binding.item?.state
                                it.binding.item?.state = !state!!
                            }
                }
                .into(recycler)
    }

    fun getCategory(item: String) {
        category = item
    }

    fun addChecklist() {
        checklistItems.add(ChecklistItem("", false))
        positionToScroll = checklistItems.lastIndex
    }

    fun setDay() {
        interactor?.setDay(DateTime.now())
    }

    fun addLocation() {
        interactor?.let { it.pickPlace().subscribe { pickedPlace = it } }
    }

    fun addFile() {

    }

    fun addPerson() {
        interactor?.let { listPerson.add(it.addPerson()) }
    }

    fun clickSave() {
        ApiRepository.saveTask(Task(1, note, category, pickedPlace?.latLng, startDay, startDay, listPerson, checklistItems))
    }
}
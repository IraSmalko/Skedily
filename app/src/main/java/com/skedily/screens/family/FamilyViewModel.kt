package com.skedily.screens.family

import android.support.v7.widget.RecyclerView

import com.github.nitrico.lastadapter.LastAdapter
import com.github.nitrico.lastadapter.Type
import com.skedily.BR
import com.skedily.R
import com.skedily.base.BaseViewModel
import com.skedily.databinding.ItemFamilyBinding
import com.skedily.model.User
import com.skedily.utils.weak

class FamilyViewModel : BaseViewModel() {
    var interactor by weak<FamilyInteractor>()
    private var userItems = listOf<User>()

    fun initRecycler(recycler: RecyclerView) {

        LastAdapter(userItems, BR.item)
                .type { _, _ ->
                    Type<ItemFamilyBinding>(R.layout.item_family)
                            .onClick {

                            }
                }
                .into(recycler)
    }

    fun onClick() {
        interactor!!.onClick()
    }
}
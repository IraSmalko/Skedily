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
    private var userItems = listOf<User>(User(0, "Shoto Tam", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", null,1),
            User(0, "Shoto Drugoe", "http://www.followingthenerd.com/site/wp-content/uploads/avatar.jpg_274898881.jpg", null,1),
            User(0, "Shoto Drugoe", "https://media-curse.cursecdn.com/attachments/268/106/e24d53fb955e8cce0a236f742259c34b.jpeg", null,1))

    fun initRecycler(recycler: RecyclerView) {

        LastAdapter(userItems, BR.item)
                .type { _, _ ->
                    Type<ItemFamilyBinding>(R.layout.item_family)
                }
                .into(recycler)
    }

    fun addPerson() {

    }
}
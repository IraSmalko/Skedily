package com.skedily.screens.add_card

import android.os.Bundle
import android.os.PersistableBundle
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityAddCardBinding

/**
 * Created by smalk on 11/29/2017.
 */
class AddCardActivity : BaseBoundVmActivity<ActivityAddCardBinding, AddCardViewModel>(
        R.layout.activity_add_card, AddCardViewModel::class), AddCardInteractor {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        vm
    }
}
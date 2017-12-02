package com.skedily.screens.add_card

import android.os.Bundle
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityAddCardBinding
import kotlinx.android.synthetic.main.activity_add_card.*

/**
 * Created by smalk on 11/29/2017.
 */
class AddCardActivity : BaseBoundVmActivity<ActivityAddCardBinding, AddCardViewModel>(
        R.layout.activity_add_card, AddCardViewModel::class), AddCardInteractor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.initRecycler(recycler)
    }
}
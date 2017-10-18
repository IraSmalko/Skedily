package com.skedily.screens.family

import android.os.Bundle
import android.widget.Toast
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityFamilyBinding
import kotlinx.android.synthetic.main.activity_family.*

class FamilyActivity : BaseBoundVmActivity<ActivityFamilyBinding, FamilyViewModel>(
R.layout.activity_family, FamilyViewModel::class), FamilyInteractor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.interactor = this
        vm.initRecycler(recycler)
    }

    override fun onClick() {
        Toast.makeText(applicationContext, "Click", Toast.LENGTH_SHORT).show()
    }
}

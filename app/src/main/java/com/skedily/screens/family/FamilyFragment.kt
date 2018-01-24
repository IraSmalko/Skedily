package com.skedily.screens.family

import android.os.Bundle
import android.view.View
import com.skedily.R
import com.skedily.base.BaseBoundVmFragment
import com.skedily.databinding.FragmentFamilyBinding
import kotlinx.android.synthetic.main.fragment_family.*

/**
 * Created by smalk on 1/18/2018.
 */
class FamilyFragment : BaseBoundVmFragment<FragmentFamilyBinding, FamilyViewModel>(
        R.layout.fragment_family, FamilyViewModel::class), FamilyInteractor {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.interactor = this
        vm.initRecycler(recycler)
    }

}
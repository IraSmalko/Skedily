package com.skedily.screens.schedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.skedily.R
import com.skedily.base.BaseBoundVmFragment
import com.skedily.databinding.FragmentScheduleBinding
import com.skedily.repository.MockApiRepositoryImpl
import com.skedily.screens.add_card.AddCardActivity
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * Created by smalk on 1/24/2018.
 */
class ScheduleFragment : BaseBoundVmFragment<FragmentScheduleBinding, ScheduleViewModel>(
        R.layout.fragment_schedule, ScheduleViewModel::class), ScheduleInteractor {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.interactor = this
        vm.init(MockApiRepositoryImpl().getTacks(context!!))
        vm.initRecyclers(recycler, recyclerCalendar)
    }

    override fun openAddCardScreen() {
        startActivity(Intent(context, AddCardActivity::class.java))
    }

    override fun openTack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
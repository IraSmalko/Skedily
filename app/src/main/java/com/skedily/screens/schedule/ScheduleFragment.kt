package com.skedily.screens.schedule

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.skedily.R
import com.skedily.base.BaseBoundVmFragment
import com.skedily.databinding.FragmentScheduleBinding
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : BaseBoundVmFragment<FragmentScheduleBinding, ScheduleViewModel>(
        R.layout.fragment_schedule, ScheduleViewModel::class), SheduleInteractor {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.interactor = this
//        vm.init(mutableListOf(
//                Task(
//                        1,
//                        "Mock mock mock",
//                        null,
//                        DateTime(2017, 9, 24, 22, 55),
//                        DateTime(2017, 9, 24, 23, 55),
//                        mutableListOf<User>(User(55, "", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", Location(""), ContextCompat.getColor(context!!, R.color.user1)))
//                ),
//                Task(
//                        2,
//                        "Title and title",
//                        null,
//                        DateTime(2017, 11, 22, 22, 55),
//                        DateTime(2017, 11, 23, 23, 55),
//                        mutableListOf<User>(User(55, "", "", Location(""), ContextCompat.getColor(context!!, R.color.manyUsers)))
//                ),
//                Task(
//                        3,
//                        "Test test test test test  test",
//                        null,
//                        DateTime(2017, 11, 25, 19, 55),
//                        DateTime(2017, 11, 23, 21, 55),
//                        mutableListOf<User>(User(55, "", "http://www.followingthenerd.com/site/wp-content/uploads/avatar.jpg_274898881.jpg", Location(""), ContextCompat.getColor(context!!, R.color.user2)))
//                ),
//                Task(
//                        4,
//                        "Buy meat",
//                        null,
//                        DateTime(2017, 11, 25, 19, 55),
//                        DateTime(2017, 11, 25, 21, 55),
//                        mutableListOf<User>(User(55, "", "https://media-curse.cursecdn.com/attachments/268/106/e24d53fb955e8cce0a236f742259c34b.jpeg", Location(""), ContextCompat.getColor(context!!, R.color.user3)))
//                )))
        vm.initRecycler(recycler)

    }

    override fun onClick() {
        Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()
    }
}

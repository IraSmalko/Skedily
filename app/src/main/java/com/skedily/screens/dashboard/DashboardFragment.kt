package com.skedily.screens.dashboard

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.skedily.R
import com.skedily.base.BaseBoundVmFragment
import com.skedily.databinding.FragmentDashboardBinding
import com.skedily.model.ChecklistItem
import com.skedily.model.Task
import com.skedily.model.User
import com.skedily.screens.add_card.AddCardActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.joda.time.DateTime

class DashboardFragment : BaseBoundVmFragment<FragmentDashboardBinding, DashboardViewModel>(
        R.layout.fragment_dashboard, DashboardViewModel::class), DashboardInteractor {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.interactor = this
        vm.init(mutableListOf(
                Task(
                        1,
                        "Mock mock mock",
                        "",
                        null,
                        DateTime(2017, 9, 24, 22, 55),
                        DateTime(2017, 9, 24, 23, 55),
                        mutableListOf<User>(User(55, "", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", Location(""), ContextCompat.getColor(context!!, R.color.user1))),
                        ArrayList<ChecklistItem>()),
                Task(
                        2,
                        "Title and title",
                        "",
                        null,
                        DateTime(2017, 11, 22, 22, 55),
                        DateTime(2017, 11, 23, 23, 55),
                        mutableListOf<User>(User(55, "", "", Location(""), ContextCompat.getColor(context!!, R.color.manyUsers))),
                        ArrayList<ChecklistItem>()
                ),
                Task(
                        3,
                        "Test test test test test  test",
                        "",
                        null,
                        DateTime(2017, 11, 25, 19, 55),
                        DateTime(2017, 11, 23, 21, 55),
                        mutableListOf<User>(User(55, "", "http://www.followingthenerd.com/site/wp-content/uploads/avatar.jpg_274898881.jpg", Location(""), ContextCompat.getColor(context!!, R.color.user2))),
                        ArrayList<ChecklistItem>()),
                Task(
                        4,
                        "Buy meat",
                        "",
                        null,
                        DateTime(2017, 11, 25, 19, 55),
                        DateTime(2017, 11, 25, 21, 55),
                        mutableListOf<User>(User(55, "", "https://media-curse.cursecdn.com/attachments/268/106/e24d53fb955e8cce0a236f742259c34b.jpeg", Location(""), ContextCompat.getColor(context!!, R.color.user3))),
                        ArrayList<ChecklistItem>()),
                Task(
                        1,
                        "Mock mock mock",
                        "",
                        null,
                        DateTime(2017, 9, 24, 22, 55),
                        DateTime(2017, 9, 24, 23, 55),
                        mutableListOf<User>(User(55, "", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", Location(""), ContextCompat.getColor(context!!, R.color.user1))),
                        ArrayList<ChecklistItem>()),
                Task(
                        2,
                        "Title and title",
                        "",
                        null,
                        DateTime(2017, 11, 22, 22, 55),
                        DateTime(2017, 11, 23, 23, 55),
                        mutableListOf<User>(User(55, "", "", Location(""), ContextCompat.getColor(context!!, R.color.manyUsers))),
                        ArrayList<ChecklistItem>()
                ),
                Task(
                        3,
                        "Test test test test test  test",
                        "",
                        null,
                        DateTime(2017, 11, 25, 19, 55),
                        DateTime(2017, 11, 23, 21, 55),
                        mutableListOf<User>(User(55, "", "http://www.followingthenerd.com/site/wp-content/uploads/avatar.jpg_274898881.jpg", Location(""), ContextCompat.getColor(context!!, R.color.user2))),
                        ArrayList<ChecklistItem>()),
                Task(
                        4,
                        "Buy meat",
                        "",
                        null,
                        DateTime(2017, 11, 25, 19, 55),
                        DateTime(2017, 11, 25, 21, 55),
                        mutableListOf<User>(User(55, "", "https://media-curse.cursecdn.com/attachments/268/106/e24d53fb955e8cce0a236f742259c34b.jpeg", Location(""), ContextCompat.getColor(context!!, R.color.user3))),
                        ArrayList<ChecklistItem>())))
        vm.initRecycler(recycler)

    }

    override fun openAddCardScreen() {
        startActivity(Intent(context, AddCardActivity::class.java))
    }

    override fun openTack() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

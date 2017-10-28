package com.skedily.screens.schedule

import android.location.Address
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityScheduleBinding
import com.skedily.model.Task
import com.skedily.model.User
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_schedule.*
import org.joda.time.DateTime
import java.util.*

class ScheduleActivity : BaseBoundVmActivity<ActivityScheduleBinding, ScheduleViewModel>(
        R.layout.activity_schedule, ScheduleViewModel::class), SheduleInteractor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.interactor = this
        vm.taskItems = mutableListOf(
                Task(1, "Dkmjhbasduhio iodsj", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                null, true,  DateTime(2017, 9, 24, 22, 55), DateTime(2017, 9, 24, 23, 55),
                false, User(55, "", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", Location(""), ContextCompat.getColor(this, R.color.colorAccent))),
                Task(2, "Dkmjhbasduhio iodsj", "https://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                        null, true,  DateTime(2017, 11, 23, 22, 55), DateTime(2017, 11, 23, 23, 55),
                        false, User(55, "", "https://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", Location(""), ContextCompat.getColor(this, R.color.colorAccent))),
                Task(3, "Test test test test test  test", "https://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                        null, true,  DateTime(2017, 11, 23, 19, 55), DateTime(2017, 11, 23, 21, 55),
                        false, User(55, "", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", Location(""), ContextCompat.getColor(this, R.color.colorAccent)))

        )
        vm.initRecycler(recycler)

    }

    override fun onClick() {
        Toast.makeText(applicationContext, "Click", Toast.LENGTH_SHORT).show()
    }
}

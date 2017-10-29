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
                Task(1, "Mock mock mock", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                        null, true, DateTime(2017, 9, 24, 22, 55), DateTime(2017, 9, 24, 23, 55),
                        false, User(55, "", "http://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4", Location(""), ContextCompat.getColor(this, R.color.user1))),
                Task(2, "Title and title", "https://cdn.pixabay.com/photo/2016/03/31/19/57/avatar-1295406_960_720.png",
                        null, true, DateTime(2017, 11, 22, 22, 55), DateTime(2017, 11, 23, 23, 55),
                        false, User(55, "", "", Location(""), ContextCompat.getColor(this, R.color.manyUsers))),
                Task(3, "Test test test test test  test", "https://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                        null, true, DateTime(2017, 11, 25, 19, 55), DateTime(2017, 11, 23, 21, 55),
                        false, User(55, "", "http://www.followingthenerd.com/site/wp-content/uploads/avatar.jpg_274898881.jpg", Location(""), ContextCompat.getColor(this, R.color.user2))),
                Task(4, "Buy meat", "https://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                        null, true, DateTime(2017, 11, 25, 19, 55), DateTime(2017, 11, 25, 21, 55),
                        false, User(55, "", "https://media-curse.cursecdn.com/attachments/268/106/e24d53fb955e8cce0a236f742259c34b.jpeg", Location(""), ContextCompat.getColor(this, R.color.user3))),
                Task(2, "Title and title", "https://cdn.pixabay.com/photo/2016/03/31/19/57/avatar-1295406_960_720.png",
                        null, true, DateTime(2017, 11, 13, 22, 55), DateTime(2017, 11, 23, 23, 55),
                        false, User(55, "", "", Location(""), ContextCompat.getColor(this, R.color.manyUsers))),
                Task(3, "Test test test test test  test", "https://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                        null, true, DateTime(2017, 11, 11, 19, 55), DateTime(2017, 11, 23, 21, 55),
                        false, User(55, "", "http://www.followingthenerd.com/site/wp-content/uploads/avatar.jpg_274898881.jpg", Location(""), ContextCompat.getColor(this, R.color.user2))),
                Task(4, "Buy meat", "https://avatars3.githubusercontent.com/u/22910919?s=400&u=94b560541ddb1d811ba700dea56375db1b0a2b57&v=4",
                        null, true, DateTime(2017, 11, 11, 19, 55), DateTime(2017, 11, 25, 21, 55),
                        false, User(55, "", "https://media-curse.cursecdn.com/attachments/268/106/e24d53fb955e8cce0a236f742259c34b.jpeg", Location(""), ContextCompat.getColor(this, R.color.user3)))

        )
        vm.initRecycler(recycler)

    }

    override fun onClick() {
        Toast.makeText(applicationContext, "Click", Toast.LENGTH_SHORT).show()
    }
}

package com.skedily.screens.add_card

import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.github.florent37.singledateandtimepicker.dialog.DoubleDateAndTimePickerDialog
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityAddCardBinding
import com.skedily.model.User
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Maybe
import kotlinx.android.synthetic.main.activity_add_card.*
import org.joda.time.DateTime


/**
 * Created by smalk on 11/29/2017.
 */
class AddCardActivity : BaseBoundVmActivity<ActivityAddCardBinding, AddCardViewModel>(
        R.layout.activity_add_card, AddCardViewModel::class), AddCardInteractor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.init(this)
        vm.initRecycler(recycler)
    }

    override fun pickPlace(): Maybe<Place> {
        val builder = PlacePicker.IntentBuilder()
        RxPermissions(this)
                .request(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                .filter { it }
                .subscribe({ _ ->
                    startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST)
                })
        return awaitActivityResult(PLACE_PICKER_REQUEST).map<Place> { PlacePicker.getPlace(this, it.data) }

    }

    override fun addPerson(): User {

        return User(1, " ", "", Location(""), 1)
    }

    override fun setDay(today: DateTime) {
        DoubleDateAndTimePickerDialog.Builder(this)
                .backgroundColor(ContextCompat.getColor(this, R.color.white))
                .mainColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .minutesStep(15)
                .tab0Text("start")
                .tab1Text("end")
                .listener({
                    vm.startDay = it[0].let { DateTime(it) }
                    vm.endDay = it[1].let { DateTime(it) }
                }).display()
    }

    companion object {
        const val PLACE_PICKER_REQUEST = 132
    }
}
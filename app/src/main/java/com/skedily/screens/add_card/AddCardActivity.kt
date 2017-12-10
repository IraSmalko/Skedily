package com.skedily.screens.add_card

import android.location.Location
import android.os.Bundle
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.skedily.R
import com.skedily.base.BaseBoundVmActivity
import com.skedily.databinding.ActivityAddCardBinding
import com.skedily.model.User
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
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
        SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback({ _, y, m, d ->  vm.startDay = DateTime(y, m, d, 0, 0)})
                .spinnerTheme(R.style.NumberPickerStyle)
                .defaultDate(today.year, today.monthOfYear - 1, today.dayOfMonth)
                .build()
                .show()
    }

    companion object {
        const val PLACE_PICKER_REQUEST = 132
    }
}
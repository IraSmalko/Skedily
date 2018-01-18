package com.skedily.screens.add_card

import com.google.android.gms.location.places.Place
import com.skedily.model.User
import io.reactivex.Maybe
import org.joda.time.DateTime

/**
 * Created by smalk on 11/29/2017.
 */
interface AddCardInteractor {
    fun pickPlace() : Maybe<Place>
    fun addPerson() : User
    fun setDay(today: DateTime)
}
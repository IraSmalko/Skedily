package com.skedily.screens.add_card

import com.google.android.gms.location.places.Place
import io.reactivex.Maybe

/**
 * Created by smalk on 11/29/2017.
 */
interface AddCardInteractor {
    fun pickPlace() : Maybe<Place>
}
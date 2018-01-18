package com.skedily.model

import android.location.Location
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class User(
        val id: Int,
        val name: String,
        val iconUrl: String,
        val location: Location?,
        val color: Int
) : PaperParcelable {
    companion object {
        @JvmField
        val CREATOR = PaperParcelUser.CREATOR
    }
}

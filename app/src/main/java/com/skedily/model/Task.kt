package com.skedily.model

import android.location.Address
import org.joda.time.DateTime
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import android.location.Location

@PaperParcel
data class Task(
        val id: Int,
        val name: String,
        val iconUrl: String,
        val address: Address?,
        var firstOfDay: Boolean = true,
        val startTime: DateTime,
        val endTime: DateTime,
        var isSoon: Boolean = false,
        val user: User
) : PaperParcelable {
    companion object {
        @JvmField
        val CREATOR = PaperParcelTask.CREATOR
    }
}

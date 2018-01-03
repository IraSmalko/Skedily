package com.skedily.model

import android.content.res.Resources
import com.google.android.gms.maps.model.LatLng
import com.skedily.R
import org.joda.time.DateTime
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Task(
        val id: Int,
        val name: String,
        val address: LatLng?,
        val startTime: DateTime,
        val endTime: DateTime,
        val listPerson: List<User>,
        val checklist: ArrayList<ChecklistItem>
) : PaperParcelable {

    val color: Int
        get() = if (listPerson.size != 1) R.color.manyUsers else listPerson.first().color

    val photo: String
        get() = if (listPerson.size != 1) "" else listPerson.first().iconUrl

    companion object {
        @JvmField
        val CREATOR = PaperParcelTask.CREATOR
    }
}


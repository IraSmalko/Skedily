package com.skedily.model

import com.google.android.gms.maps.model.LatLng
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
        val checklist: List<ChecklistItem>
) : PaperParcelable {
    companion object {
        @JvmField
        val CREATOR = PaperParcelTask.CREATOR
    }
}


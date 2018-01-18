package com.skedily.repository

import com.google.firebase.database.FirebaseDatabase
import com.skedily.model.Task

class MainApiRepository : IApiRepository {

    override fun saveTask(task: Task) {
        FirebaseDatabase.getInstance().reference.child(groupUrl).setValue(task.toPost())
    }

}
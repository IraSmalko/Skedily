package com.skedily.repository

import com.google.firebase.database.FirebaseDatabase
import com.skedily.model.Group
import com.skedily.model.Task

class MainApiRepository : IApiRepository {

    override fun createGroup(name:String) {
        val ref = FirebaseDatabase.getInstance().getReference(userId + "/group")
        val key = ref.push().key
        ref.setValue(Group(key, name))
    }

    override fun saveTask(task: Task) {
        val ref = FirebaseDatabase.getInstance().reference.child("task")
        ref.child(groupUrl).setValue(task.toPost())
    }

}
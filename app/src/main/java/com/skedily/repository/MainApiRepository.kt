package com.skedily.repository

//class MainApiRepository : IApiRepository {
//
//    override fun createGroup(name:String) {
//        val ref = FirebaseDatabase.getInstance().getReference(userId + "/group")
//        val key = ref.push().key
//        key?.let { ref.setValue(Group(key, name))}
//    }
//
//    override fun saveTask(task: Task) {
//        val ref = FirebaseDatabase.getInstance().reference.child("task")
//        ref.child(groupUrl).setValue(task.toPost())
//    }
//
//}
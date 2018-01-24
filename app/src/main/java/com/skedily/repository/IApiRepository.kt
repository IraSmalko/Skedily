package com.skedily.repository

import com.skedily.model.Task

interface IApiRepository {

    fun createGroup(name:String)
    fun saveTask(task: Task)

}
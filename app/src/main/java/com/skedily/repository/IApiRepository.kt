package com.skedily.repository

import com.skedily.model.Task

interface IApiRepository {

    fun saveTask(task: Task)

}
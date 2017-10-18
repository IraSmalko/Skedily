package com.skedily.utils

import com.skedily.repository.IApiRepository
import com.skedily.repository.MockApiRepositoryImpl

object ApiRepository : IApiRepository by MockApiRepositoryImpl()

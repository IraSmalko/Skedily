package com.skedily.utils

import com.skedily.repository.IApiRepository
import com.skedily.repository.MainApiRepository

object ApiRepository : IApiRepository by MainApiRepository()

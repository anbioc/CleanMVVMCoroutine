package com.aba.core.data.repository

import com.aba.core.base.ResultResponse

interface JobRepository {
    suspend fun getJobs(description: String, location: String): ResultResponse
}
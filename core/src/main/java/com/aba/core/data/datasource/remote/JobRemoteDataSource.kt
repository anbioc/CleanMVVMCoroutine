package com.aba.core.data.datasource.remote

import com.aba.core.base.BaseDataSource
import com.aba.core.base.ResultResponse

interface JobRemoteDataSource : BaseDataSource {
    suspend fun getJobs(
        description: String,
        location: String
    ): ResultResponse
}
package com.aba.core.data.datasource.local

import com.aba.core.base.BaseDataSource
import com.aba.core.base.ResultResponse
import com.aba.core.data.model.JobItem

interface JobLocalDataSource: BaseDataSource {
    suspend fun insertOrUpdate(jobItem: List<JobItem>)

    suspend fun getAll(): ResultResponse

    suspend fun get(description: String): ResultResponse

}
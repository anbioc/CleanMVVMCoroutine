package com.aba.core.data.repository

import com.aba.core.base.BaseRepository
import com.aba.core.base.QueryParam
import com.aba.core.base.QueryStrategy
import com.aba.core.base.ResultResponse
import com.aba.core.data.model.JobItem
import kotlinx.coroutines.flow.Flow

abstract class JobRepository<PARAM: QueryParam>: BaseRepository<PARAM> {
    abstract suspend fun getJobs(param: PARAM, strategy: QueryStrategy): Flow<ResultResponse>
}
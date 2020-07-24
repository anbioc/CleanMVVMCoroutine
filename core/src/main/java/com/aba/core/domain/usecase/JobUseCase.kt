package com.aba.core.domain.usecase

import com.aba.core.base.BaseUseCase
import com.aba.core.base.QueryParam
import com.aba.core.base.QueryStrategy
import com.aba.core.base.ResultResponse
import com.aba.core.data.repository.JobRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class JobUseCase @Inject constructor(
    private val repository: JobRepository<JobUseCase.JobSearchParams>
) : BaseUseCase.ResourceUseCase<JobUseCase.JobSearchParams> {

    override suspend fun executeAsync(
        params: JobSearchParams,
        strategy: QueryStrategy
    ): Flow<ResultResponse> =
        withContext(Dispatchers.IO) {
            repository.getJobs(params, strategy)
        }


    class JobSearchParams(val description: String, val location: String) :
        QueryParam
}
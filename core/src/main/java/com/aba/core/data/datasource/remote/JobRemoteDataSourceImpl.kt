package com.aba.core.data.datasource.remote

import com.aba.core.base.ResultResponse
import com.aba.core.data.net.JobsService
import javax.inject.Inject

class JobRemoteDataSourceImpl @Inject constructor(
    private val jobService: JobsService
) : JobRemoteDataSource {
    override suspend fun getJobs(
        description: String,
        location: String
    ): ResultResponse = jobService.getJobs(
        description, location
    ).run {
        if (isSuccessful) {
            ResultResponse.Success(this.body())
        } else {
            ResultResponse.Failure(Throwable(message()))
        }
    }

}
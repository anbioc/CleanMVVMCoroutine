package com.aba.core.data.repository

import com.aba.core.base.ResultResponse
import com.aba.core.data.datasource.local.JobLocalDataSource
import com.aba.core.data.datasource.remote.JobRemoteDataSource
import com.aba.core.data.mapper.JobMapper
import com.aba.core.data.model.JobItem
import javax.inject.Inject

class JobRepositoryImpl @Inject constructor(
    private val remoteDataSource: JobRemoteDataSource,
    private val localDataSource: JobLocalDataSource,
    private val jobMapper: JobMapper
) : JobRepository {
    override suspend fun getJobs(description: String, location: String): ResultResponse =
        with(localDataSource.get(description)) {
            return if (isEmpty().not()) {
                jobMapper.map(this)
            } else {
                remoteDataSource.getJobs(description, location).run {
                    return if (this.isSuccess()) {
                        localDataSource.insertOrUpdate((this as ResultResponse.Success<List<JobItem>>).data)
                        jobMapper.map(this)
                    } else {
                        this
                    }
                }
            }

        }
}
package com.aba.core.data.datasource.local

import com.aba.core.base.ResultResponse
import com.aba.core.data.datasource.dao.JobDao
import com.aba.core.data.model.JobItem
import javax.inject.Inject

class JobLocalDataSourceImpl @Inject constructor(
    private val jobDao: JobDao
) : JobLocalDataSource {
    override suspend fun insertOrUpdate(jobItem: List<JobItem>) = jobDao.insertOrUpdate(jobItem)

    override suspend fun getAll() = jobDao.getAll().run {
        ResultResponse.Success(this)
    }

    override suspend fun get(description: String) = jobDao.get(description).run {
        ResultResponse.Success(this)
    }
}
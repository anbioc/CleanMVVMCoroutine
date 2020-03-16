package com.aba.core.data.net

import com.aba.core.data.model.JobItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JobsService {

    @GET("positions.json")
    suspend fun getJobs(
        @Query("description") description: String,
        @Query("location") location: String
    ): Response<List<JobItem>>

}
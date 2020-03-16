package com.aba.core.data.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aba.core.BuildConfig
import com.aba.core.data.model.JobItem

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(jobItem: List<JobItem>)

    @Query("SELECT * FROM ${BuildConfig.TABLE_NAME_JOB}")
    suspend fun getAll(): List<JobItem>

    @Query("SELECT * FROM ${BuildConfig.TABLE_NAME_JOB} WHERE description like '%'||:description||'%' ")
    suspend fun get(description: String): List<JobItem>

}
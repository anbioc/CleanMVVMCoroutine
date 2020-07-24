package com.aba.core.di

import androidx.annotation.NonNull
import com.aba.core.data.datasource.dao.JobDao
import com.aba.core.data.datasource.local.JobLocalDataSource
import com.aba.core.data.datasource.local.JobLocalDataSourceImpl
import com.aba.core.data.datasource.remote.JobRemoteDataSource
import com.aba.core.data.datasource.remote.JobRemoteDataSourceImpl
import com.aba.core.data.mapper.JobMapper
import com.aba.core.data.net.JobsService
import com.aba.core.data.repository.JobRepository
import com.aba.core.data.repository.JobRepositoryImpl
import com.aba.core.domain.usecase.JobUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideJobRepository(
        @NonNull jobRemoteDataSource: JobRemoteDataSource,
        @NonNull jobLocalDataSource: JobLocalDataSource,
        @NonNull jobMapper: JobMapper
    ): JobRepository<JobUseCase.JobSearchParams> = JobRepositoryImpl(
        jobRemoteDataSource,
        jobLocalDataSource,
        jobMapper
    )

    @Provides
    @Singleton
    fun provideJobRemoteDataSource(
        @NonNull jobService: JobsService
    ): JobRemoteDataSource = JobRemoteDataSourceImpl(
        jobService
    )

    @Provides
    @Singleton
    fun provideJobMapper(): JobMapper = JobMapper()

    @Provides
    @Singleton
    fun provideJobLocalDataSource(
        @NonNull jobDao: JobDao
    ): JobLocalDataSource = JobLocalDataSourceImpl(
        jobDao
    )
}
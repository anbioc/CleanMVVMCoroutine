package com.aba.core.di

import androidx.annotation.NonNull
import com.aba.core.data.repository.JobRepository
import com.aba.core.domain.usecase.JobUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideJobUseCase(@NonNull jobRepository: JobRepository): JobUseCase = JobUseCase(jobRepository)
}
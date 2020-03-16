package com.aba.core.di

import android.content.Context
import com.aba.core.data.datasource.AppDatabase
import com.aba.core.data.datasource.dao.JobDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.getAppInstance(context)

    @Provides
    @Singleton
    fun provideJobDao(appDatabase: AppDatabase): JobDao = appDatabase.jobDao()
}
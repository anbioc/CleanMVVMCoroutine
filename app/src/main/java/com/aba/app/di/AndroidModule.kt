package com.aba.app.di

import android.app.Application
import android.content.Context
import com.aba.app.navigation.AppFragmentNavigationHelper
import com.aba.app.theme.AppTheme
import com.aba.core.util.FragmentNavigationHelper
import com.aba.lib_theme.ThemeProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideFragmentNavigator(): FragmentNavigationHelper = AppFragmentNavigationHelper()


    @Provides
    @Singleton
    fun provideTheme(): ThemeProvider = AppTheme()
}
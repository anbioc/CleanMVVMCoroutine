package com.aba.search.di

import com.aba.core.di.scopes.PerActivity
import com.aba.search.presentation.MainNavigationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBiding {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun ContributesMainActivity(): MainNavigationActivity
}
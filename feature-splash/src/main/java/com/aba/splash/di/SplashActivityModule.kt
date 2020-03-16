package com.aba.splash.di

import com.aba.core.di.scopes.PerActivity
import com.aba.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashActivityModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity
}
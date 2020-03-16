package com.aba.search.di

import com.aba.core.di.scopes.PerFragment
import com.aba.search.di.SearchFragmentModule
import com.aba.search.presentation.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainActivityModule {

//    @PerFragment
//    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
//    abstract fun contributeSearchFragment(): SearchFragment

//    @PerFragment
//    @ContributesAndroidInjector
//    abstract fun contributeSearchFragment(): SearchFragment
}
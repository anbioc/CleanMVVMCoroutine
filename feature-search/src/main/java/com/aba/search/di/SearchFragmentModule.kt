package com.aba.search.di

import android.content.Context
import com.aba.core.di.scopes.PerFragment
import com.aba.search.presentation.SearchFragment
import com.aba.search.presentation.list.SearchListAdapter
import com.aba.search.presentation.list.SearchListAdapterImpl
import dagger.Module
import dagger.Provides

@Module
class SearchFragmentModule {

    @PerFragment
    @Provides
    fun provideContext(fragment: SearchFragment): Context = fragment.requireContext()

    @PerFragment
    @Provides
    fun provideJobAdapter(adapter: SearchListAdapterImpl): SearchListAdapter = adapter

    @PerFragment
    @Provides
    fun provideAdapterCallback(fragment: SearchFragment): SearchListAdapter.Callback = fragment
}
package com.aba.app.di

import android.app.Application
import com.aba.core.di.*
import com.aba.core.di.scopes.PerApplication
import com.aba.search.di.MainActivityBiding
import com.aba.search.di.SearchFragmentBinding
import com.aba.splash.di.SplashActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@PerApplication
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidModule::class,
        DataModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        UseCaseModule::class,
        ViewModelModule::class,
        SplashActivityModule::class,
        MainActivityBiding::class,
        SearchFragmentBinding::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication)

}
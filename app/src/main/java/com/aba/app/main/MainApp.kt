package com.aba.app.main


import com.aba.app.di.AppComponent
import com.aba.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication



class MainApp : DaggerApplication() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

}
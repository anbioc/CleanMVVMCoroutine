package com.aba.core.robolectric

import android.app.Activity
import android.app.Application
import com.aba.core.R
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

open class TestApp: Application(), HasActivityInjector {

    private val fakeActivityInjector: AndroidInjector<Activity> = AndroidInjector { }

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.AppTheme); //or just R.style.Theme_AppCompat
    }

    override fun activityInjector(): AndroidInjector<Activity> = fakeActivityInjector

}

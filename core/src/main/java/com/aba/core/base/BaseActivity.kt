package com.aba.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aba.core.extension.makeFullScreen
import com.aba.core.util.FragmentNavigationHelper
import com.aba.lib_theme.ThemeProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DaggerActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(){

    @Inject
    lateinit var themeProvider: ThemeProvider

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var fragmentNavigator: FragmentNavigationHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.makeFullScreen()
        AndroidInjection.inject(this)
        applyAppTheme()
    }


    private fun applyAppTheme() {
        setTheme(themeProvider.theme)
    }

}
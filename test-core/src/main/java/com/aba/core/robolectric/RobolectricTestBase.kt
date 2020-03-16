package com.aba.core.robolectric

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import junit.framework.Assert.fail
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Using a temporary work-around robolectric test runner due to the Robolectric issue in
 * https://github.com/robolectric/robolectric/issues/3659
 * Revert back to the normal RobolectricTestRunner if/when this issue gets fixed.
 */
//@RunWith(DependencyFileLockingRobolectricTestRunner::class)
@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class, sdk = [26])
//@Config(application = TestApp::class, constants = BuildConfig::class, sdk = [26])
abstract class RobolectricTestBase {


    protected fun getString(@StringRes resId: Int): String {
        return RuntimeEnvironment.application.getString(resId)
    }

    protected fun getString(@StringRes resId: Int, vararg args: Any): String {
        return RuntimeEnvironment.application.getString(resId, *args)
    }

    protected fun getQuantityString(
        @PluralsRes resId: Int, quantity: Int,
        vararg args: Any
    ): String {
        return RuntimeEnvironment.application.resources.getQuantityString(resId, quantity, *args)
    }


    protected class TestActivity<VIEW: View> : Activity() {
        lateinit var view: View
            private set
        private lateinit var constructor: ViewTestBase.CreateViewCallback<VIEW>

        fun setConstructor(callback: ViewTestBase.CreateViewCallback<VIEW>) {
            constructor = callback
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            val layout = LinearLayout(this)

            setContentView(layout)

            layout.orientation = LinearLayout.VERTICAL

            if (constructor != null) {
                view = constructor!!.call(this)
            } else {
                fail("could not create view")
            }

            layout.addView(view)
        }
    }

}
package com.aba.core.robolectric

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.mockito.MockitoAnnotations.initMocks
import org.robolectric.Robolectric
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowInputMethodManager

abstract class ViewTestBase<VIEW: View>: RobolectricTestBase() {


    private lateinit var activity: TestActivity<VIEW>
    private lateinit var  shadowActivity: ShadowActivity
    private lateinit var  shadowInputMethodManager: ShadowInputMethodManager
    private lateinit var  view: VIEW

    fun createView(constructor: CreateViewCallback<VIEW>) {
        val controller = Robolectric.buildActivity(TestActivity::class.java)

        activity = controller.get() as TestActivity<VIEW>
        activity.setConstructor(constructor)
        shadowActivity = shadowOf(activity)

        initMocks(this)

        controller.create()

        val inputManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        shadowInputMethodManager = shadowOf(inputManager)

        view = activity.view as VIEW
    }

    interface CreateViewCallback<VIEW : View> {
        fun call(context: Context): VIEW
    }


}
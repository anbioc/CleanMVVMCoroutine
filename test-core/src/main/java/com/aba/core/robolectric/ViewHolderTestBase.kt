package com.aba.core.robolectric

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import org.mockito.MockitoAnnotations
import org.robolectric.RuntimeEnvironment

abstract class ViewHolderTestBase<VH : RecyclerView.ViewHolder> : RobolectricTestBase() {

    protected val context: Context = RuntimeEnvironment.application
    protected lateinit var subject: VH

    protected fun createViewHolder(@LayoutRes layoutRes: Int,
                                   createViewHolderAction: (view: View) -> VH) {
        val subjectView = LayoutInflater.from(context).inflate(layoutRes, null)
        MockitoAnnotations.initMocks(this)
        subject = createViewHolderAction(subjectView)
    }
}

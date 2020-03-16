package com.aba.search.presentation.list

import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.aba.core.SOME_JOB_DOMAIN_MODEL
import com.aba.core.SOME_JOB_DOMAIN_MODELS
import com.aba.core.argumentCaptor
import com.aba.core.capture
import com.aba.core.domain.data.JobDomainModel
import com.aba.core.robolectric.RobolectricTestBase
import com.aba.core.util.ImageLoader
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.assertNotNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.MockitoAnnotations.initMocks
import org.robolectric.RuntimeEnvironment

class SearchListAdapterImplTest : RobolectricTestBase() {

    private val mockCallback: SearchListAdapter.Callback = mock()

    private val mockImageLoader: ImageLoader = mock()

    private val mockViewHolder: JobItemViewHolder = mock()

    private lateinit var subject: SearchListAdapterImpl
    private lateinit var resultViewHolder: RecyclerView.ViewHolder

    @Before
    fun `setup`() {
        initMocks(this)
        subject = SearchListAdapterImpl(mockCallback, mockImageLoader)
    }

    @Test
    fun `whenOnCreateViewHolder thenSearchItemViewHolderIsCreated`() {
        whenOnCreateViewHolder()
        thenSearchItemViewHolderIsCreated()
    }

    @Test
    fun `givenJobItems whenOnBindViewHolder thenJobItemViewHolderBindView`(){
        givenJobItems()
        whenOnBindViewHolder()
        thenJobItemViewHolderBindView()
    }

    @Test
    fun `givenJobItems thenGetItemCountIsReturnedJobItemsSize`(){
        givenJobItems()
        thenGetItemCountIsReturnedJobItemsSize()
    }

    /*
     * Given
     */
    private fun givenJobItems() {
        subject.itemList = SOME_JOB_DOMAIN_MODELS
    }

    /*
     * When
     */
    private fun whenOnCreateViewHolder() {
        resultViewHolder = subject.createViewHolder(FrameLayout(RuntimeEnvironment.application), 0)
    }

    private fun whenOnBindViewHolder() {
        subject.onBindViewHolder(mockViewHolder, 0)
    }


    /*
     * Then
     */
    private fun thenSearchItemViewHolderIsCreated() {
        assertNotNull(resultViewHolder)
    }

    private fun thenJobItemViewHolderBindView() {
        with(argumentCaptor<JobDomainModel>()){
            then(mockViewHolder).should().bind(capture(this))
            assertThat(value).isEqualTo(SOME_JOB_DOMAIN_MODEL)
        }
    }

    private fun thenGetItemCountIsReturnedJobItemsSize() {
        assertThat(SOME_JOB_DOMAIN_MODELS.size).isEqualTo(subject.itemCount)
    }
}
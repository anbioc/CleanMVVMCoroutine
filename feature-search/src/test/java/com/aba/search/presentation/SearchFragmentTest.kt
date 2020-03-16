package com.aba.search.presentation

import android.widget.FrameLayout
import android.widget.ViewSwitcher
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aba.core.SOME_JOB_DOMAIN_MODELS
import com.aba.core.base.ResultResponse
import com.aba.core.robolectric.FragmentTestBase
import com.aba.feature_search.R
import com.aba.search.presentation.list.SearchListAdapter
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.spy
import com.nhaarman.mockitokotlin2.then
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.inc_search_list.*
import kotlinx.android.synthetic.main.inc_search_submit.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations.initMocks
import org.assertj.core.api.Assertions.assertThat
import org.assertj.android.api.Assertions.assertThat


class SearchFragmentTest : FragmentTestBase<SearchFragment>() {

    private val mockViewModel: SearchViewModel = mock()
    private val mockViewModelFactory: ViewModelProvider.Factory = mock()
    private val mockAdapter: SearchListAdapter = mock()
    private var jobLiveData = MutableLiveData<ResultResponse>()
    private lateinit var spyFragment: SearchFragment
    private lateinit var spyViewModel: SearchViewModel
    private lateinit var result: ResultResponse
    private lateinit var spyRecyclerView: RecyclerView

    private val loadingProgress by lazy {
        fragment.view?.findViewById<FrameLayout>(R.id.errorLoadingView)
    }

    private val viewSwitcher by lazy {
        fragment.view?.findViewById<ViewSwitcher>(R.id.errorSuccessViewSwitcher)
    }

    @Before
    fun `setup`() {
        initMocks(this)
        givenViewModelProviderThenCreateViewModel()
        givenStateWillReturnActualLiveData()
        createFragment()
        spyFragment = spy(fragment)
        spyViewModel = spy(fragment.viewModel)
        spyRecyclerView = spy(fragment.jobList)
    }

    @Test
    fun `whenOnCreate thenViewIsCreatedAndVisible andThenViewModelCreated`() {
        thenViewIsCreatedAndVisible()
        thenViewModelCreated()
    }

    @Test
    fun `whenOnViewCreated thenRecyclerViewIsSetup andThenViewListenersIsSet `() {
        thenRecyclerViewIsSetup()
        thenViewListenersIsSet()
    }

    @Test
    fun `givenSuccessfulResult whenPostLiveData thenAdapterListIsFilled  andThenLoadingProgressHides`() {
        givenSuccessfulResult()
        whenPostLiveData()
        thenAdapterListIsFilled()
        thenLoadingProgressHides()
    }

    @Test
    fun `givenFailureResult whenPostLiveData thenLoadingProgressHides andThenShowErrorScreen`() {
        givenFailureResult()
        whenPostLiveData()
        thenLoadingProgressHides()
        thenShowErrorScreen()
    }

    @Test
    fun `givenSuccessfulResult whenProcessResponseResult thenAdapterListIsFilled andThenLoadingProgressHides`(){
        givenSuccessfulResult()
        whenProcessResponseResult()
        thenAdapterListIsFilled()
        thenLoadingProgressHides()
    }

    @Test
    fun `givenFailureResult whenProcessResponseResult andThenLoadingProgressHides`(){
        givenFailureResult()
        whenProcessResponseResult()
        thenLoadingProgressHides()
        thenShowErrorScreen()
    }


    /*
     * Given
     */
    private fun  givenViewModelProviderThenCreateViewModel() {
        given(mockViewModelFactory.create(SearchViewModel::class.java)).willReturn(mockViewModel)
    }

    private fun givenStateWillReturnActualLiveData() {
        given(mockViewModel.state).willReturn(jobLiveData)
    }

    private fun givenSuccessfulResult() {
        result = ResultResponse.Success(SOME_JOB_DOMAIN_MODELS)
    }

    private fun givenFailureResult() {
        result = ResultResponse.Failure(Throwable())
    }

    /*
     * When
     */
    private fun whenProcessResponseResult() {
        fragment.processResponseResult(result)
    }

    private fun whenPostLiveData() {
        jobLiveData.postValue(result)
    }

    /*
     * Then
     */
    private fun thenViewModelCreated() {
        assertThat(spyViewModel).isNotNull
    }

    private fun thenViewIsCreatedAndVisible() {
        assertThat(fragment.view).isNotNull
        assertThat(fragment.view).isVisible
    }

    private fun thenRecyclerViewIsSetup() {
        with(fragment.jobList) {
            assertThat(adapter).isEqualTo(mockAdapter)
            assertTrue(hasFixedSize())
            assertTrue(layoutManager is LinearLayoutManager)
        }
    }

    private fun thenViewListenersIsSet() {
        assertTrue(fragment.submitButton.hasOnClickListeners())
    }

    private fun thenAdapterListIsFilled() {
        then(mockAdapter).should().itemList = SOME_JOB_DOMAIN_MODELS
    }

    private fun thenLoadingProgressHides() {
        assertThat(loadingProgress).isNotVisible
    }

    private fun thenShowErrorScreen() {
        assertThat(viewSwitcher?.displayedChild).isEqualTo(1)
    }

}
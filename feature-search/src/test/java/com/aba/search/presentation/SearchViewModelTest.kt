package com.aba.search.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aba.core.*
import com.aba.core.TestUtil.Companion.anyNotNull
import com.aba.core.base.ResultResponse
import com.aba.core.domain.data.JobDomainModel
import com.aba.core.domain.usecase.JobUseCase
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutineRule()

    @Mock
    lateinit var mockJobUseCase: JobUseCase

    @Mock
    lateinit var observer: Observer<ResultResponse>

    @InjectMocks
    lateinit var subject: SearchViewModel

    @Test
    fun `whenGetData thenInitialResponseIsLoading`() = runBlocking {
        whenGetData()
        thenInitialResponseIsLoading()
    }

    @Test
    fun `givenResultIsSuccessful whenGetData thenResultIsStateResultIsSuccessful`() = runBlocking {
        givenResultIsSuccessful()
        whenGetData()
        thenUseCaseGetJobIsCalled()
        thenResultIsStateResultIsSuccessful()
    }

    /*
     * Given
     */
    private suspend fun givenResultIsSuccessful() {
        given(mockJobUseCase.executeAsync(anyNotNull())).willReturn(
            ResultResponse.Success(
                SOME_JOB_DOMAIN_MODELS
            )
        )
    }

    /*
     * When
     */
    private suspend fun whenGetData() {
        subject.getData(SOME_DESCRIPTION, SOME_LOCATION)
    }

    /*
     * Then
     */
    private suspend fun thenResultIsStateResultIsSuccessful() {
        subject.state.observeOnce {
            with((it as ResultResponse.Success<List<JobDomainModel>>).data) {
                assertThat(size).isEqualTo(1)
                with(this[0]) {
                    assertThat(id).isEqualTo(SOME_ID)
                    assertThat(company).isEqualTo(SOME_COMPANY)
                    assertThat(companyLogo).isEqualTo(SOME_COMPANY_LOGO)
                    assertThat(companyUrl).isEqualTo(SOME_COMPANY_URL)
                    assertThat(description).isEqualTo(SOME_DESCRIPTION)
                    assertThat(howToApply).isEqualTo(SOME_HOW_TO_APPLY)
                    assertThat(location).isEqualTo(SOME_LOCATION)
                    assertThat(title).isEqualTo(SOME_TITLE)
                    assertThat(url).isEqualTo(SOME_URL)
                }
            }
        }
    }

    private suspend fun thenUseCaseGetJobIsCalled() {
        verify(mockJobUseCase).executeAsync(anyNotNull())
    }

    private suspend fun thenInitialResponseIsLoading() {
        subject.state.observeOnce {
            assertThat(it.isLoading()).isTrue()
        }
    }

}
package com.aba.core.domain.usecase

import com.aba.core.*
import com.aba.core.base.ResultResponse
import com.aba.core.data.repository.JobRepository
import com.aba.core.domain.data.JobDomainModel
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JobUseCaseTest {

    @Mock
    lateinit var mockRepository: JobRepository<JobUseCase.JobSearchParams>

    @InjectMocks
    lateinit var subject: JobUseCase

    private lateinit var result: ResultResponse


    @Test
    fun `givenResultIsSuccessful whenGetJobs thenResultIsValid`() = runBlocking {
        givenResultIsSuccessful()
        whenGetJobs()
        thenResultIsValid()
    }

    @Test
    fun `givenResultIsFailure whenGetJobs thenResultIsFailure`() = runBlocking {
        givenResultIsFailure()
        whenGetJobs()
        thenResultIsFailure()
    }

    /*
     * Given
     */
    private suspend fun givenResultIsSuccessful() {
        given(mockRepository.getJobs(any(), any())).willReturn(
            flowOf(
                ResultResponse.Success(
                    SOME_JOB_DOMAIN_MODELS
                )
            )
        )
    }

    private suspend fun givenResultIsFailure() {
        given(mockRepository.getJobs(any(), any())).willReturn(
            flowOf(
                ResultResponse.Failure(
                    Throwable()
                )
            )

        )
    }

    /*
     * When
     */
    private suspend fun whenGetJobs() {
        subject.executeAsync(JOB_USE_CASE_PARAMS, SOME_OFFLINE_FIRST_STRATEGY).collect { result = it }
    }

    /*
     * Then
     */
    private fun thenResultIsValid() =
        with((result as ResultResponse.Success<List<JobDomainModel>>).data) {
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

    private suspend fun thenResultIsFailure() {
        assertThat((result as ResultResponse.Failure).isFailure()).isTrue()
    }

}
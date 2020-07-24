package com.aba.core.data.repository

import com.aba.core.*
import com.aba.core.TestUtil.Companion.anyNotNull
import com.aba.core.base.ResultResponse
import com.aba.core.coroutine.TestObserver
import com.aba.core.coroutine.test
import com.aba.core.data.datasource.local.JobLocalDataSource
import com.aba.core.data.datasource.remote.JobRemoteDataSource
import com.aba.core.data.mapper.JobMapper
import com.aba.core.data.model.JobItem
import com.aba.core.domain.data.JobDomainModel
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JobRepositoryImplTest {


    @Mock
    lateinit var mockLocalDataSource: JobLocalDataSource

    @Mock
    lateinit var mockRemoteDataSource: JobRemoteDataSource

    @Mock
    lateinit var mockJobMapper: JobMapper

    @InjectMocks
    lateinit var subject: JobRepositoryImpl

    private val values = mutableListOf<ResultResponse>()
    private val stubFailureResponse = ResultResponse.Failure(
        Throwable()
    )
    private val stubRemoteResponse = ResultResponse.Success(
        SOME_OTHER_JOB_RESPONSE_ITEMS
    )
    private val stubLocalEmptyResponse = ResultResponse.Success<List<JobItem>>(
        listOf()
    )
    private val stubLocalResponse = ResultResponse.Success(
        SOME_JOB_RESPONSE_ITEMS
    )


    @Before
    fun `setup`() {
        given(mockJobMapper.map(any())).willReturn(ResultResponse.Success(SOME_JOB_DOMAIN_MODELS))
    }


    @Test
    fun `givenLocalDataSourceResultIsAvailable whenGetJob thenLocalCacheIsUsed andThenResultIsValid`() =
        runBlocking {
            givenRemoteDataSourceResultIsFailure()
            givenLocalDataIsAvailable()
            whenGetJob(this)
            thenLocalCacheIsUsed()
            thenRemoteDataIsFailureAndLocalIsAvailable()
        }


    @Test
    fun `givenLocalDataSourceResultIsNotAvailable andGivenRemoteDataSourceResultIsAvailable whenGetJob thenRemoteDataIsUsed`() =
        runBlocking {
            givenLocalDataSourceResultIsNotAvailable()
            givenRemoteDataSourceResultIsAvailable()
            givenJobMapperMapOtherResponse()
            whenGetJob(this)
            thenRemoteDataIsUsed()
            thenDataIsSaved()
        }

    @Test
    fun `givenLocalDataSourceResultIsNotAvailable andGivenRemoteDataSourceResultIsFailure whenGetJob thenMapperIsNotCalled andThenResultIsFailure`() =
        runBlocking {
            givenLocalDataSourceResultIsNotAvailable()
            givenRemoteDataSourceResultIsFailure()
            whenGetJob(this)
            thenMapperIsNotCalled()
            thenResultIsFailure()
        }

    /*
     * Given
     */
    private suspend fun givenLocalDataIsAvailable() {
        given(mockLocalDataSource.get(SOME_DESCRIPTION)).willReturn(
            stubLocalResponse
        )
    }

    private suspend fun givenLocalDataSourceResultIsNotAvailable() {
        given(mockLocalDataSource.get(SOME_DESCRIPTION)).willReturn(
            stubLocalEmptyResponse
        )
    }

    private suspend fun givenRemoteDataSourceResultIsAvailable() {
        given(mockRemoteDataSource.getJobs(SOME_DESCRIPTION, SOME_LOCATION)).willReturn(
            stubRemoteResponse
        )
    }

    private suspend fun givenRemoteDataSourceResultIsFailure() {
        given(mockRemoteDataSource.getJobs(SOME_DESCRIPTION, SOME_LOCATION)).willReturn(
            stubFailureResponse
        )
    }

    private fun givenJobMapperMapOtherResponse() {
        given(mockJobMapper.map(any())).willReturn(
            ResultResponse.Success(
                SOME_OTHER_JOB_DOMAIN_MODELS
            )
        )
    }

    /*
     * When
     */
    private suspend fun whenGetJob(scope: CoroutineScope) =
        subject.getJobs(JOB_USE_CASE_PARAMS, SOME_OFFLINE_FIRST_STRATEGY).collect { values.add(it) }


    /*
     * Then
     */
    private suspend fun thenLocalCacheIsUsed() {
        verify(mockLocalDataSource).get(SOME_DESCRIPTION)
    }

    private fun thenRemoteDataIsFailureAndLocalIsAvailable() {
        assertThat(values[0]).isNotEqualTo(stubFailureResponse)
        assertThat(values[1]).isNotEqualTo(stubLocalResponse)

    }

    private fun thenRemoteDataIsUsed() {
        with((values[0] as ResultResponse.Success<List<JobDomainModel>>).data){
            assertThat(size).isEqualTo(0)
        }
        with((values[1] as ResultResponse.Success<List<JobDomainModel>>).data) {
            assertThat(size).isEqualTo(1)
            with(this[0]) {
                assertThat(id).isEqualTo(SOME_OTHER_ID)
                assertThat(company).isEqualTo(SOME_OTHER_COMPANY)
                assertThat(companyLogo).isEqualTo(SOME_OTHER_COMPANY_LOGO)
                assertThat(companyUrl).isEqualTo(SOME_OTHER_COMPANY_URL)
                assertThat(description).isEqualTo(SOME_OTHER_DESCRIPTION)
                assertThat(howToApply).isEqualTo(SOME_OTHER_HOW_TO_APPLY)
                assertThat(location).isEqualTo(SOME_OTHER_LOCATION)
                assertThat(title).isEqualTo(SOME_OTHER_TITLE)
                assertThat(url).isEqualTo(SOME_OTHER_URL)
            }
        }
    }

    private fun thenMapperIsNotCalled() {
        verifyZeroInteractions(mockJobMapper)
    }

    private fun thenResultIsFailure() {
        with((values[0] as ResultResponse.Success<List<JobDomainModel>>).data){
            assertThat(size).isEqualTo(0)
        }
        assertThat(values[1].isFailure()).isTrue()
    }

    private suspend fun thenDataIsSaved() {
        verify(mockLocalDataSource).insertOrUpdate(anyNotNull())
    }
}
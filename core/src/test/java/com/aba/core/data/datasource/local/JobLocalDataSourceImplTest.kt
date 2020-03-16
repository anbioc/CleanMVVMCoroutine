package com.aba.core.data.datasource.local

import com.aba.core.*
import com.aba.core.base.ResultResponse
import com.aba.core.data.datasource.dao.JobDao
import com.aba.core.data.model.JobItem
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JobLocalDataSourceImplTest {

    @Mock
    lateinit var mockJobDao: JobDao

    @InjectMocks
    lateinit var subject: JobLocalDataSourceImpl

    lateinit var result: ResultResponse

    @Test
    fun `whenInsertOrUpdate thenJobDaoInsertOrUpdateIsCalled`() = runBlocking{
        whenInsertOrUpdate()
        thenJobDaoInsertOrUpdate()
    }

    @Test
    fun `givenAllJobDaoCache whenOnGetAll thenDataIsAvailable`() = runBlocking{
        givenAllJobDaoCache()
        whenOnGetAll()
        thenJobDaoGetAllIsCalled()
        thenDataIsAvailable()
    }

    @Test
    fun `givenJobDaoCache whenOnGet thenDataIsAvailable`() = runBlocking{
        givenJobDaoCache()
        whenOnGet()
        thenJobDaoGetIsCalled()
        thenDataIsAvailable()
    }


    /*
     * Given
     */
    private suspend fun givenAllJobDaoCache() {
        given(mockJobDao.getAll()).willReturn(SOME_JOB_RESPONSE_ITEMS)
    }

    private suspend fun givenJobDaoCache() {
        given(mockJobDao.get(SOME_DESCRIPTION)).willReturn(SOME_JOB_RESPONSE_ITEMS)
    }

    /*
     * When
     */
    private suspend fun whenOnGetAll() {
        result = subject.getAll()
    }

    private suspend fun whenInsertOrUpdate() {
        subject.insertOrUpdate(SOME_JOB_RESPONSE_ITEMS)
    }

    private suspend fun whenOnGet() {
        result = subject.get(SOME_DESCRIPTION)
    }

    /*
     * Then
     */
    private fun thenDataIsAvailable() {
        with((result as ResultResponse.Success<List<JobItem>>).data){
            assertThat(size).isEqualTo(SOME_JOB_RESPONSE_ITEMS.size)
            with(this[0]){
                assertThat(title).isEqualTo(SOME_TITLE)
                assertThat(description).isEqualTo(SOME_DESCRIPTION)
                assertThat(companyLogo).isEqualTo(SOME_COMPANY_LOGO)
                assertThat(company).isEqualTo(SOME_COMPANY)
                assertThat(location).isEqualTo(SOME_LOCATION)

            }
        }
    }

    private suspend fun thenJobDaoInsertOrUpdate() {
        verify(mockJobDao).insertOrUpdate(SOME_JOB_RESPONSE_ITEMS)
    }

    private suspend fun thenJobDaoGetAllIsCalled() {
        verify(mockJobDao).getAll()
    }

    private suspend fun thenJobDaoGetIsCalled() {
        verify(mockJobDao).get(SOME_DESCRIPTION)
    }

}
package com.jesusbadenas.oompaloompascrew.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.oompaloompascrew.R
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompaDetail
import com.jesusbadenas.oompaloompascrew.domain.repositories.OompaLoompaRepository
import com.jesusbadenas.oompaloompascrew.test.CoroutinesTestRule
import com.jesusbadenas.oompaloompascrew.test.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var repository: OompaLoompaRepository

    private val oompaLoompaDetail = OompaLoompaDetail(
        id = ID,
        firstName = "John",
        lastName = "Doe",
        profession = "Metal worker",
        image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
        gender = "M",
        height = 96,
        country = "Loompaland",
        age = 22,
        email = "john.doe@charlie.com",
        description = null
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testLoadDetailSuccess() = coroutineRule.runBlockingTest {
        coEvery { repository.getOompaLoompaById(ID) } returns oompaLoompaDetail

        val viewModel = DetailViewModel(ID, repository)
        val result = viewModel.olDetail.getOrAwaitValue()

        Assert.assertNotNull(result)
        Assert.assertEquals(ID, result.id)
    }

    @Test
    fun testLoadDetailThrowsException() = coroutineRule.runBlockingTest {
        val exception = Exception()
        coEvery { repository.getOompaLoompaById(ID) } throws exception

        val viewModel = DetailViewModel(ID, repository)
        val error = viewModel.uiError.getOrAwaitValue()

        Assert.assertEquals(exception, error.throwable)
        Assert.assertEquals(R.string.generic_error_message, error.messageResId)
    }

    companion object {
        private const val ID = 1
    }
}

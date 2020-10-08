package com.jesusbadenas.oompaloompascrew.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
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
class ListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var repository: OompaLoompaRepository

    private val oompaLoompa = OompaLoompa(
        id = 1,
        firstName = "John",
        lastName = "Doe",
        profession = "Metal worker",
        image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
        gender = "M"
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { repository.getOompaLoompas(1) } returns listOf(oompaLoompa)
        coEvery { repository.getOompaLoompas(2) } returns listOf(oompaLoompa.copy(id = 2))
    }

    @Test
    fun testLoadFirstPageSuccess() = coroutineRule.runBlockingTest {
        val viewModel = ListViewModel(repository)

        val result = viewModel.list.getOrAwaitValue()

        Assert.assertNotNull(result)
        Assert.assertEquals(1, result.size)
        Assert.assertEquals(1, result[0].id)
        Assert.assertFalse(viewModel.isLoading)
    }

    @Test
    fun testLoadSecondPageSuccess() = coroutineRule.runBlockingTest {
        val viewModel = ListViewModel(repository)

        viewModel.loadMoreItems()
        val result = viewModel.list.getOrAwaitValue()

        Assert.assertNotNull(result)
        Assert.assertEquals(2, result.size)
        Assert.assertEquals(1, result[0].id)
        Assert.assertEquals(2, result[1].id)
        Assert.assertFalse(viewModel.isLoading)
    }
}

package com.jesusbadenas.oompaloompascrew.domain.repositories

import com.jesusbadenas.oompaloompascrew.data.api.APIService
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompaDetail
import com.jesusbadenas.oompaloompascrew.data.entities.response.FavoriteData
import com.jesusbadenas.oompaloompascrew.data.entities.response.OompaLoompaData
import com.jesusbadenas.oompaloompascrew.data.entities.response.OompaLoompasResponse
import com.jesusbadenas.oompaloompascrew.test.CoroutinesTestRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OompaLoompaRepositoryTest {

    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    @MockK
    private lateinit var apiService: APIService

    private lateinit var repository: OompaLoompaRepository

    private val data = OompaLoompaData(
        id = 1,
        firstName = "John",
        lastName = "Doe",
        profession = "Metal worker",
        image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
        gender = "M",
        height = 96,
        country = "Loompaland",
        age = 22,
        email = "john.doe@charlie.com",
        favorite = FavoriteData(color = "red", food = "nuts", random = "", song = "")
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = OompaLoompaRepository(apiService)
    }

    @Test
    fun testGetOompaLoompasSuccess() {
        val response = OompaLoompasResponse(current = 1, total = 1, listOf(data))
        coEvery { apiService.getOompaLoompas(1) } returns response

        val result = runBlocking { repository.getOompaLoompas(1) }
        val expected = OompaLoompa(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            profession = "Metal worker",
            image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
            gender = "M"
        )

        Assert.assertNotNull(result)
        Assert.assertEquals(1, result!!.size)
        Assert.assertEquals(expected, result[0])
    }

    @Test
    fun testGetOompaLoompaByIdSuccess() {
        coEvery { apiService.getOompaLoompaById(1) } returns data

        val result = runBlocking { repository.getOompaLoompaById(1) }
        val expected = OompaLoompaDetail(
            id = 1,
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

        Assert.assertEquals(expected, result)
    }
}

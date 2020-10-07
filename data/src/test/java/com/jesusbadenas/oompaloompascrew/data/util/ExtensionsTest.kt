package com.jesusbadenas.oompaloompascrew.data.util

import com.jesusbadenas.oompaloompascrew.data.entities.OompaLoompa
import com.jesusbadenas.oompaloompascrew.data.entities.response.FavoriteData
import com.jesusbadenas.oompaloompascrew.data.entities.response.OompaLoompaData
import org.junit.Assert
import org.junit.Test

class ExtensionsTest {

    @Test
    fun testOompaLoompaConversion() {
        val data = OompaLoompaData(
            id = 1,
            firstName = "John",
            lastName = "Doe",
            height = 96,
            country = "Loompaland",
            age = 22,
            email = "john.doe@charlie.com",
            profession = "Metal worker",
            image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
            gender = "M",
            favorite = FavoriteData(color = "red", food = "nuts", random = "", song = "")
        )

        val result = data.toOompaLoompa()
        val expected = OompaLoompa(
            id = 1,
            firstName = "John",
            lastName = "Doe", profession = "Metal worker",
            image = "https://s3.eu-central-1.amazonaws.com/napptilus/level-test/2.jpg",
            gender = "M"
        )

        Assert.assertEquals(expected, result)
    }
}

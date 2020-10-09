package com.jesusbadenas.oompaloompascrew.extensions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.jesusbadenas.oompaloompascrew.test.getOrAwaitValue
import com.jesusbadenas.oompaloompascrew.util.addMoreItems
import com.jesusbadenas.oompaloompascrew.util.clearAndAdd
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ExtensionsTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val intList = listOf(1, 2, 3)

    @Test
    fun testListClearAndAddSuccess() {
        val list = mutableListOf(0)
        list.clearAndAdd(intList)
        Assert.assertEquals(intList, list)
    }

    @Test
    fun testLiveDataAddMoreItemsSuccess() {
        val liveDataList = MutableLiveData<MutableList<Int>>()
        liveDataList.addMoreItems(intList)

        val result = liveDataList.getOrAwaitValue()

        Assert.assertEquals(intList, result)
    }
}

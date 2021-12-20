package com.nils.marsrover.model.type

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertFailsWith

@RunWith(SpringRunner::class)
internal class OrientTypeUnitTest {

    @Test
    fun test_get_orientation_by_key_null() {
        assertFailsWith<NoSuchElementException>(
            block = { OrientationType.getOrientationByKey('X') }
        )
    }

    @Test
    fun test_get_orientation_by_key_success() {
        val orientation = OrientationType.getOrientationByKey('S')
        Assert.assertTrue("Unexpected orientation type value for null case", OrientationType.SOUTH == orientation)
    }
}
package com.nils.marsrover.model

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
internal class CoordinateUnitTest {

    @Test
    fun test_is_equal_false_different_x() {
        val coordinate1 = Coordinate(3, 5)
        val coordinate2 = Coordinate(5, 5)
        val result = coordinate1 == coordinate2
        Assert.assertTrue("Unexpected result for isEqual when x values are different", !result)
    }

    @Test
    fun test_is_equal_false_different_y() {
        val coordinate1 = Coordinate(3, 5)
        val coordinate2 = Coordinate(3, 6)
        val result: Boolean = coordinate1 == coordinate2
        Assert.assertTrue("Unexpected result for isEqual when y values are different", !result)
    }

    @Test
    fun test_is_equal_false_both_different() {
        val coordinate1 = Coordinate(3, 5)
        val coordinate2 = Coordinate(5, 6)
        val result: Boolean = coordinate1 == coordinate2
        Assert.assertTrue("Unexpected result for isEqual when both values are different", !result)
    }

    @Test
    fun test_is_equal_true() {
        val coordinate1 = Coordinate(5, 5)
        val coordinate2 = Coordinate(5, 5)
        val result: Boolean = coordinate1 == coordinate2
        Assert.assertTrue("Unexpected result for isEqual when both values are actually equal", result)
    }

    @Test
    fun test_to_string() {
        val coordinate = Coordinate(3, 5)
        val result = coordinate.toString()
        Assert.assertTrue("Unexpected result for toString", "3 5" == result)
    }
}
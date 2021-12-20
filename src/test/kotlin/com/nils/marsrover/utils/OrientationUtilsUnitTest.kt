package com.nils.marsrover.utils

import com.nils.marsrover.model.type.OrientationType
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
internal class OrientationUtilsUnitTest {

    @Test
    fun test_move_left_for_north() {
        val orientation = OrientationType.NORTH
        val result = OrientationUtils.moveLeft(orientation)
        Assert.assertTrue("Unexpected moveLeft return value for North", OrientationType.WEST == result)
    }

    @Test
    fun test_move_left_for_west() {
        val orientation = OrientationType.WEST
        val result = OrientationUtils.moveLeft(orientation)
        Assert.assertTrue("Unexpected moveLeft return value for West", OrientationType.SOUTH == result)
    }

    @Test
    fun test_move_left_for_east() {
        val orientation = OrientationType.EAST
        val result = OrientationUtils.moveLeft(orientation)
        Assert.assertTrue("Unexpected moveLeft return value for East", OrientationType.NORTH == result)
    }

    @Test
    fun test_move_left_for_south() {
        val orientation = OrientationType.SOUTH
        val result = OrientationUtils.moveLeft(orientation)
        Assert.assertTrue("Unexpected moveLeft return value for South", OrientationType.EAST == result)
    }

    @Test
    fun test_move_right_for_north() {
        val orientation = OrientationType.NORTH
        val result = OrientationUtils.moveRight(orientation)
        Assert.assertTrue("Unexpected moveRight return value for North", OrientationType.EAST == result)
    }

    @Test
    fun test_move_right_for_west() {
        val orientation = OrientationType.WEST
        val result = OrientationUtils.moveRight(orientation)
        Assert.assertTrue("Unexpected moveRight return value for West", OrientationType.NORTH == result)
    }

    @Test
    fun test_move_right_for_east() {
        val orientation = OrientationType.EAST
        val result = OrientationUtils.moveRight(orientation)
        Assert.assertTrue("Unexpected moveRight return value for East", OrientationType.SOUTH == result)
    }

    @Test
    fun test_move_right_for_south() {
        val orientation = OrientationType.SOUTH
        val result = OrientationUtils.moveRight(orientation)
        Assert.assertTrue("Unexpected moveRight return value for South", OrientationType.WEST == result)
    }
}
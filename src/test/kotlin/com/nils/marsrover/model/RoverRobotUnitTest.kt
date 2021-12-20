package com.nils.marsrover.model

import com.nils.marsrover.model.type.OrientationType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
internal class RoverRobotUnitTest {

    private lateinit var roverRobot: RoverRobot

    @Before
    fun setUp() {
        roverRobot = RoverRobot(Coordinate(2, 3), OrientationType.NORTH)
    }

    @Test
    fun test_is_on_coordinate_true() {
        val coordinate = Coordinate(2, 3)
        val result = roverRobot.isOnCoordinate(coordinate)
        Assert.assertTrue("Unexpected result for isOnCoordinate for value true", result)
    }

    @Test
    fun test_is_on_coordinate_false() {
        val coordinate = Coordinate(2, 4)
        val result = roverRobot.isOnCoordinate(coordinate)
        Assert.assertTrue("Unexpected result for isOnCoordinate for value false", !result)
    }
}
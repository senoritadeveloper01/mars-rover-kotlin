package com.nils.marsrover.utils

import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau
import com.nils.marsrover.model.RoverRobot
import com.nils.marsrover.model.type.OrientationType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
internal class PositionUtilsUnitTest {

    private lateinit var roverRobot1: RoverRobot
    private lateinit var roverRobot2: RoverRobot

    @Before
    fun setUp() {
        roverRobot1 = RoverRobot(Coordinate(2, 3), OrientationType.NORTH)
        roverRobot2 = RoverRobot(Coordinate(5, 6), OrientationType.NORTH)
    }

    @Test
    fun test_is_occupied_true_first_value() {
        val plateau = Plateau(Coordinate(8, 8), mutableListOf(roverRobot1, roverRobot2))
        val result = PositionUtils.isOccupied(plateau, Coordinate(2, 3))
        Assert.assertTrue( "Unexpected isOccupied return value for first rover robot that occupies actually", result)
    }

    @Test
    fun test_is_occupied_true_second_value() {
        val plateau = Plateau(Coordinate(8, 8), mutableListOf(roverRobot1, roverRobot2))
        val result = PositionUtils.isOccupied(plateau, Coordinate(5, 6))
        Assert.assertTrue("Unexpected isOccupied return value for second rover robot that occupies actually", result)
    }

    @Test
    fun test_is_occupied_false() {
        val plateau = Plateau(Coordinate(8, 8), mutableListOf(roverRobot1, roverRobot2))
        val result = PositionUtils.isOccupied(plateau, Coordinate(4, 3))
        Assert.assertTrue("Unexpected isOccupied return value for non-occupied coordinates", result == false)
    }
}
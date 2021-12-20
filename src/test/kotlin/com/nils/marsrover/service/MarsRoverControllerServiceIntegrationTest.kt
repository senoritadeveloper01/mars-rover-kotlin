package com.nils.marsrover.service

import com.nils.marsrover.model.type.OrientationType
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
internal class MarsRoverControllerServiceIntegrationTest {

    private lateinit var marsRoverControllerService: MarsRoverControllerService

    @Before
    fun setUp() {
        marsRoverControllerService = MarsRoverControllerService(InputValidationService(),  PlateauService(),  RoverRobotService())
    }

    @Test
    fun test_solve() {
        var roverRobot = marsRoverControllerService.solve(5, 5, 1, 2, 'N', "LMLMLMLMM")
        Assert.assertTrue("Unexpected X coordinate", roverRobot.coordinate.x == 1)
        Assert.assertTrue("Unexpected Y coordinate", roverRobot.coordinate.y == 3)
        Assert.assertTrue("Unexpected orientation", OrientationType.NORTH == roverRobot.orientation)

        roverRobot = marsRoverControllerService.solve(5, 5, 3, 3, 'E', "MMRMMRMRRM")
        Assert.assertTrue("Unexpected X coordinate", roverRobot.coordinate.x == 5)
        Assert.assertTrue("Unexpected Y coordinate", roverRobot.coordinate.y == 1)
        Assert.assertTrue("Unexpected orientation", OrientationType.EAST == roverRobot.orientation)
    }
}
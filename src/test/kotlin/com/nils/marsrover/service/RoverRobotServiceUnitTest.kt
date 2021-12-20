package com.nils.marsrover.service

import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau
import com.nils.marsrover.model.RoverRobot
import com.nils.marsrover.model.type.CommandType
import com.nils.marsrover.model.type.OrientationType
import com.nils.marsrover.utils.OrientationUtils
import io.mockk.*
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.reflect.Whitebox
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ExtendWith(MockKExtension::class)
@PrepareForTest(RoverRobotService::class, OrientationUtils::class)
internal class RoverRobotServiceUnitTest {

    @SpyK(recordPrivateCalls = true)
    var roverRobotService = RoverRobotService()

    @Before
    fun setUp() {
        // PowerMockito.mockStatic(OrientationUtils::class.java)
        mockkObject(OrientationUtils)
    }

    @Test
    fun test_rover_robot_init() {
        val dimension = Coordinate(3, 5)
        val orientation = OrientationType.SOUTH
        val roverRobot = roverRobotService.initializeRoverRobot(dimension, orientation)

        Assert.assertTrue("Unexpected rover robot coordinate X value", roverRobot.coordinate.x == 3)
        Assert.assertTrue("Unexpected rover robot coordinate Y value", roverRobot.coordinate.y == 5)
        Assert.assertTrue("Unexpected rover robot orientation", OrientationType.SOUTH == roverRobot.orientation)
    }

    @Test
    fun test_is_able_to_move_north_false() {
        val roverRobot = RoverRobot(Coordinate(3, 6), OrientationType.SOUTH)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveNorth", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for invalid move for North", isAble == false)
    }

    @Test
    fun test_is_able_to_move_north_true() {
        val roverRobot = RoverRobot(Coordinate(3, 5), OrientationType.SOUTH)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveNorth", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for valid move for North", isAble == true)
    }


    @Test
    fun test_is_able_to_move_south_false() {
        val roverRobot = RoverRobot(Coordinate(6, 0), OrientationType.SOUTH)
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveSouth", roverRobot)
        Assert.assertTrue("Unexpected output for invalid move for South", isAble == false)
    }

    @Test
    fun test_is_able_to_move_south_true() {
        val roverRobot = RoverRobot(Coordinate(5, 1), OrientationType.SOUTH)
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveSouth", roverRobot)
        Assert.assertTrue("Unexpected output for valid move for South", isAble == true)
    }

    @Test
    fun test_is_able_to_move_east_false() {
        val roverRobot = RoverRobot(Coordinate(6, 5), OrientationType.SOUTH)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveEast", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for invalid move for East", isAble == false)
    }

    @Test
    fun test_is_able_to_move_east_true() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.SOUTH)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveEast", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for valid move for East", isAble == true)
    }

    @Test
    fun test_is_able_to_move_west_false() {
        val roverRobot = RoverRobot(Coordinate(0, 5), OrientationType.SOUTH)
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveWest", roverRobot)
        Assert.assertTrue("Unexpected output for invalid move for West", isAble == false)
    }

    @Test
    fun test_is_able_to_move_west_true() {
        val roverRobot = RoverRobot(Coordinate(1, 5), OrientationType.SOUTH)
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMoveWest", roverRobot)
        Assert.assertTrue("Unexpected output for valid move for West", isAble == true)
    }

    @Test
    fun test_is_able_to_move_north_is_called() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.NORTH)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())

        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        Whitebox.invokeMethod<Boolean>(mock, "isAbleToMove", roverRobot, plateau)

        verify(exactly = 1) {
            mock["isAbleToMoveNorth"](any<RoverRobot>(), any<Plateau>())
        }
        verify(exactly = 0) {
            mock["isAbleToMoveSouth"](any<RoverRobot>())
        }
        verify(exactly = 0) {
            mock["isAbleToMoveEast"](any<RoverRobot>(), any<Plateau>())
        }
        verify(exactly = 0) {
            mock["isAbleToMoveWest"](any<RoverRobot>())
        }
    }

    @Test
    fun test_is_able_to_move_south_is_called() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.SOUTH)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())

        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        Whitebox.invokeMethod<RoverRobot>(mock, "isAbleToMove", roverRobot, plateau)

        verify(exactly = 0) {
            mock["isAbleToMoveNorth"](any<RoverRobot>(), any<Plateau>())
        }
        verify(exactly = 1) {
            mock["isAbleToMoveSouth"](any<RoverRobot>())
        }
        verify(exactly = 0) {
            mock["isAbleToMoveEast"](any<RoverRobot>(), any<Plateau>())
        }
        verify(exactly = 0) {
            mock["isAbleToMoveWest"](any<RoverRobot>())
        }
    }

    @Test
    fun test_is_able_to_move_east_is_called() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.EAST)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())

        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        Whitebox.invokeMethod<Boolean>(mock, "isAbleToMove", roverRobot, plateau)

        verify(exactly = 0) {
            mock["isAbleToMoveNorth"](roverRobot, plateau)
        }
        verify(exactly = 0) {
            mock["isAbleToMoveSouth"](roverRobot)
        }
        verify(exactly = 1) {
            mock["isAbleToMoveEast"](roverRobot, plateau)
        }
        verify(exactly = 0) {
            mock["isAbleToMoveWest"](roverRobot)
        }
    }

    @Test
    fun test_is_able_to_move_west_is_called() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.WEST)
        val plateau = Plateau(Coordinate(6, 6), ArrayList())

        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        Whitebox.invokeMethod<Boolean>(mock, "isAbleToMove", roverRobot, plateau)

        verify(exactly = 0) {
            mock["isAbleToMoveNorth"](roverRobot, plateau)
        }
        verify(exactly = 0) {
            mock["isAbleToMoveSouth"](roverRobot)
        }
        verify(exactly = 0) {
            mock["isAbleToMoveEast"](roverRobot, plateau)
        }
        verify(exactly = 1) {
            mock["isAbleToMoveWest"](roverRobot)
        }
    }

    @Test
    fun test_is_able_to_move_north_is_occupied() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.NORTH)
        val previousRoverRobot = RoverRobot(Coordinate(5, 6), OrientationType.EAST)
        val plateau = Plateau(Coordinate(6, 6), mutableListOf(previousRoverRobot))
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMove", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for move North occupied case", isAble == false)
    }

    @Test
    fun test_is_able_to_move_south_is_occupied() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.SOUTH)
        val previousRoverRobot = RoverRobot(Coordinate(5, 4), OrientationType.EAST)
        val plateau = Plateau(Coordinate(6, 6), mutableListOf(previousRoverRobot))
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMove", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for move South occupied case", isAble == false)
    }

    @Test
    fun test_is_able_to_move_east_is_occupied() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.EAST)
        val previousRoverRobot = RoverRobot(Coordinate(6, 5), OrientationType.EAST)
        val plateau = Plateau(Coordinate(6, 6), mutableListOf(previousRoverRobot))
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMove", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for move East occupied case",isAble == false)
    }

    @Test
    fun test_is_able_to_move_west_is_occupied() {
        val roverRobot = RoverRobot(Coordinate(5, 5), OrientationType.WEST)
        val previousRoverRobot = RoverRobot(Coordinate(4, 5), OrientationType.EAST)
        val plateau = Plateau(Coordinate(6, 6), mutableListOf(previousRoverRobot))
        val isAble = Whitebox.invokeMethod<Boolean>(roverRobotService, "isAbleToMove", roverRobot, plateau)
        Assert.assertTrue("Unexpected output for move West occupied case",isAble == false)
    }

    @Test
    fun test_move_north() {
        var roverRobot = RoverRobot(Coordinate(1, 1), OrientationType.NORTH)
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot)
        Assert.assertTrue("Unexpected output for move North",roverRobot.coordinate.y == 2
        )
    }

    @Test
    fun test_move_south() {
        var roverRobot = RoverRobot(Coordinate(1, 1), OrientationType.SOUTH)
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot)
        Assert.assertTrue("Unexpected output for move South", roverRobot.coordinate.y == 0)
    }

    @Test
    fun test_move_east() {
        var roverRobot = RoverRobot(Coordinate(1, 1), OrientationType.EAST)
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot)
        Assert.assertTrue("Unexpected output for move East", roverRobot.coordinate.x == 2)
    }

    @Test
    fun test_move_west() {
        var roverRobot = RoverRobot(Coordinate(1, 1), OrientationType.WEST)
        roverRobot = Whitebox.invokeMethod(roverRobotService, "move", roverRobot)
        Assert.assertTrue("Unexpected output for move West", roverRobot.coordinate.x == 0)
    }

    @Test
    fun test_rotate_left() {
        val roverRobot = RoverRobot(Coordinate(1, 1), OrientationType.WEST)
        val command = CommandType.LEFT

        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        Whitebox.invokeMethod<RoverRobot>(mock, "rotate", roverRobot, command)

        verify(exactly = 1) {
            OrientationUtils["moveLeft"](any<OrientationType>())
        }
    }

    @Test
    fun test_rotate_right() {
        val roverRobot = RoverRobot(Coordinate(1, 1), OrientationType.WEST)
        val command = CommandType.RIGHT

        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        Whitebox.invokeMethod<RoverRobot>(mock, "rotate", roverRobot, command)

        verify(exactly = 1) {
            OrientationUtils["moveRight"](any<OrientationType>())
        }
    }

    @Test
    fun test_take_action_move_nok() {
        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        every { mock invoke "isAbleToMove"  withArguments listOf( any<RoverRobot>(),  any<Plateau>()) } returns false

        val command = CommandType.MOVE
        val plateau = Plateau(Coordinate(3, 3), ArrayList())
        val roverRobot = RoverRobot(Coordinate(1, 2), OrientationType.EAST)
        mock.takeAction(plateau, roverRobot, command)

        verify(exactly = 0) {
            mock["move"](any<RoverRobot>())
        }
        verify(exactly = 0) {
            mock["rotate"](any<RoverRobot>(), any<CommandType>())
        }
    }

    @Test
    fun test_take_action_move_ok() {
        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        every { mock invoke "isAbleToMove"  withArguments listOf( any<RoverRobot>(),  any<Plateau>()) } returns true

        val command = CommandType.MOVE
        val plateau = Plateau(Coordinate(3, 3), ArrayList())
        val roverRobot = RoverRobot(Coordinate(1, 2), OrientationType.EAST)
        mock.takeAction(plateau, roverRobot, command)

        verify(exactly = 1) {
            mock["move"](any<RoverRobot>())
        }
        verify(exactly = 0) {
            mock["rotate"](any<RoverRobot>(), any<CommandType>())
        }
    }

    @Test
    fun test_take_action_rotate_ok() {
        val mock = spyk(RoverRobotService(), recordPrivateCalls = true)
        every { mock invoke "isAbleToMove"  withArguments listOf( any<RoverRobot>(),  any<Plateau>()) } returns true

        val command = CommandType.RIGHT
        val plateau = Plateau(Coordinate(3, 3), ArrayList())
        val roverRobot = RoverRobot(Coordinate(1, 2), OrientationType.EAST)
        mock.takeAction(plateau, roverRobot, command)

        verify(exactly = 0) {
            mock["move"](any<RoverRobot>())
        }
        verify(exactly = 1) {
            mock["rotate"](any<RoverRobot>(), any<CommandType>())
        }
    }
}
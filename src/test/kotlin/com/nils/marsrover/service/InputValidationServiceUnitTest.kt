package com.nils.marsrover.service

import com.nils.marsrover.exception.MarsRoverException
import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau
import com.nils.marsrover.model.RoverRobot
import com.nils.marsrover.model.type.OrientationType
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertFailsWith

@RunWith(SpringRunner::class)
@ExtendWith(MockKExtension::class)
internal class InputValidationServiceUnitTest {

    @InjectMockKs
    var inputValidationService = InputValidationService()

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun test_validate_invalid_plateau_dimension_x_negative() {
        val e = assertFailsWith<MarsRoverException>(
            block = { inputValidationService.validatePlateau(-5, 8) }
        )
        Assert.assertTrue(
            "Unexpected error msg for plateau negative dimension X",
            e.message!!.contains("Plateau coordinates cannot be negative, x: -5, y: 8")
        )
    }

    @Test
    fun test_validate_invalid_plateau_dimension_y_negative() {
        val e = assertFailsWith<MarsRoverException> (
            block = { inputValidationService.validatePlateau(5, -8) }
        )
        Assert.assertTrue(
            "Unexpected error msg for plateau negative dimension Y",
            e.message!!.contains("Plateau coordinates cannot be negative, x: 5, y: -8")
        )
    }

    @Test
    fun test_validate_valid_plateau_dimensions() {
        inputValidationService.validatePlateau(5, 8)
    }

    @Test
    fun test_validate_invalid_initial_state_coordinate_x_negative() {
        val e = assertFailsWith<MarsRoverException> (
            block = { inputValidationService.validateInitialState(-5, 8, 'N') }
        )
        Assert.assertTrue(
            "Unexpected error msg for rover robot coordinate X",
            e.message!!.contains("Initial coordinates cannot be negative, x: -5, y: 8")
        )
    }

    @Test
    fun test_validate_invalid_initial_state_coordinate_y_negative() {
        val e = assertFailsWith<MarsRoverException> (
            block = { inputValidationService.validateInitialState(5, -8, 'N') }
        )
        Assert.assertTrue(
            "Unexpected error msg for rover robot coordinate Y",
            e.message!!.contains("Initial coordinates cannot be negative, x: 5, y: -8")
        )
    }

    @Test
    fun test_validate_invalid_orientation() {
        val e = assertFailsWith<MarsRoverException> (
            block = { inputValidationService.validateInitialState(5, 8, 'X') }
        )
        Assert.assertTrue(
            "Unexpected error msg for rover robot orientation",
            e.message!!.contains("Invalid orientation (possible values include 'N'-'S'-'E'-'W'), orientation: X")
        )
    }

    @Test
    fun test_validate_valid_initial_state() {
        inputValidationService.validateInitialState(5, 8, 'W')
    }

    @Test
    fun test_validate_invalid_commands() {
        val e = assertFailsWith<MarsRoverException> (
            block = { inputValidationService.validateCommands(charArrayOf('L', 'X', 'R')) }
        )
        Assert.assertTrue(
            "Unexpected error msg for invalid command",
            e.message!!.contains("Invalid command (possible values include 'L'-'R'-'M'), command : X")
        )
    }

    @Test
    fun test_validate_valid_commands() {
        inputValidationService.validateCommands(charArrayOf('L', 'M', 'R'))
    }

    @Test
    fun test_validate_invalid_occupied_coordinate() {
        val roverRobot = RoverRobot(Coordinate(2, 3), OrientationType.WEST)
        val plateau = Plateau(Coordinate(3, 4), arrayListOf(roverRobot))
        val roverRobotCoordinate = Coordinate(2, 3)

        val e = assertFailsWith<MarsRoverException> (
            block = { inputValidationService.validateIfCoordinateIsUnoccupied(plateau, roverRobotCoordinate) }
        )
        Assert.assertTrue(
            "Unexpected error msg for invalid occupied coordinate",
            e.message!!.contains("Position is occupied by previously moved rover robot: x: 2, y: 3")
        )
    }

    @Test
    fun test_validate_valid_unoccupied_coordinate() {
        val roverRobot = RoverRobot(Coordinate(2, 3), OrientationType.WEST)
        val plateau = Plateau(Coordinate(3, 4), arrayListOf(roverRobot))
        val roverRobotCoordinate = Coordinate(3, 3)

        inputValidationService.validateIfCoordinateIsUnoccupied(plateau, roverRobotCoordinate)
    }
}
package com.nils.marsrover.service

import com.nils.marsrover.exception.MarsRoverException
import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau
import com.nils.marsrover.model.error.MarsRoverErrorCode
import com.nils.marsrover.model.type.CommandType
import com.nils.marsrover.model.type.OrientationType
import com.nils.marsrover.utils.PositionUtils
import org.springframework.stereotype.Service

/**
 * Request validation class
 * @author  Nil Seri
 */
@Service
class InputValidationService {

    fun validatePlateau(dimensionX: Int, dimensionY: Int) {
        if (dimensionX < 0 || dimensionY < 0) {
            throw MarsRoverException(MarsRoverErrorCode.PLATEAU_COORDINATES_CANNOT_BE_NEGATIVE, dimensionX, dimensionY)
        }
    }

    fun validateInitialState(x: Int, y: Int, orientation: Char) {
        validateCoordinates(x, y)
        validateOrientation(orientation)
    }

    private fun validateCoordinates(x: Int, y: Int) {
        if (x < 0 || y < 0) {
            throw MarsRoverException(MarsRoverErrorCode.INITIAL_COORDINATES_CANNOT_BE_NEGATIVE, x, y)
        }
    }

    private fun validateOrientation(orientation: Char) {
        try {
            OrientationType.getOrientationByKey(orientation) == null
        } catch (e: NoSuchElementException) {
            throw MarsRoverException(MarsRoverErrorCode.INVALID_ORIENTATION, orientation)
        }
    }

    fun validateCommands(commands: CharArray) {
        commands.forEach { command ->
            try {
                CommandType.getCommandByKey(command)
            } catch (e: NoSuchElementException) {
                throw MarsRoverException(MarsRoverErrorCode.INVALID_COMMAND, command)
            }
        }
    }

    /**
     * Check if previously released rover robots block the moving one
     */
    fun validateIfCoordinateIsUnoccupied(plateau: Plateau, coordinate: Coordinate) {
        if (PositionUtils.isOccupied(plateau, coordinate)) {
            throw MarsRoverException(MarsRoverErrorCode.POSITION_IS_OCCUPIED, coordinate.x, coordinate.y)
        }
    }
}
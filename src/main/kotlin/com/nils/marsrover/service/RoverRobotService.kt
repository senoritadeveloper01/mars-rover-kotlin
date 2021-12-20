package com.nils.marsrover.service

import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau
import com.nils.marsrover.model.RoverRobot
import com.nils.marsrover.model.type.CommandType
import com.nils.marsrover.model.type.OrientationType
import com.nils.marsrover.utils.OrientationUtils
import com.nils.marsrover.utils.PositionUtils
import org.springframework.stereotype.Service

/**
 * Class handling rover robot related functions
 * @author  Nil Seri
 */
@Service
class RoverRobotService {

    fun initializeRoverRobot(coordinate: Coordinate, orientation: OrientationType): RoverRobot {
        return RoverRobot(coordinate, orientation)
    }

    fun takeAction(plateau: Plateau, roverRobot: RoverRobot, command: CommandType): RoverRobot {
        if (CommandType.MOVE == command) {
            // if the rover robot is not able to move, do nothing
            if (isAbleToMove(roverRobot, plateau)) {
                return move(roverRobot)
            }
        } else {
            return rotate(roverRobot, command)
        }
        return roverRobot
    }

    private fun isAbleToMove(roverRobot: RoverRobot, plateau: Plateau): Boolean {
        // also check if the previous rover is on the spot as well as the plateau edges
        return when (roverRobot.orientation) {
            OrientationType.NORTH -> isAbleToMoveNorth(roverRobot, plateau) && !PositionUtils.isOccupied(
                plateau,
                Coordinate(roverRobot.coordinate.x, roverRobot.coordinate.y + 1)
            )
            OrientationType.SOUTH -> isAbleToMoveSouth(roverRobot) && !PositionUtils.isOccupied(
                plateau,
                Coordinate(roverRobot.coordinate.x, roverRobot.coordinate.y - 1)
            )
            OrientationType.EAST -> isAbleToMoveEast(roverRobot, plateau) && !PositionUtils.isOccupied(
                plateau,
                Coordinate(roverRobot.coordinate.x + 1, roverRobot.coordinate.y)
            )
            OrientationType.WEST -> isAbleToMoveWest(roverRobot) && !PositionUtils.isOccupied(
                plateau,
                Coordinate(roverRobot.coordinate.x - 1, roverRobot.coordinate.y)
            )
        }
    }

    private fun isAbleToMoveNorth(roverRobot: RoverRobot, plateau: Plateau): Boolean {
        return roverRobot.coordinate.y + 1 <= plateau.dimension.y
    }

    private fun isAbleToMoveSouth(roverRobot: RoverRobot): Boolean {
        return roverRobot.coordinate.y - 1 >= 0
    }

    private fun isAbleToMoveEast(roverRobot: RoverRobot, plateau: Plateau): Boolean {
        return roverRobot.coordinate.x + 1 <= plateau.dimension.x
    }

    private fun isAbleToMoveWest(roverRobot: RoverRobot): Boolean {
        return roverRobot.coordinate.x - 1 >= 0
    }

    private fun move(roverRobot: RoverRobot): RoverRobot {
        val (x, y) = roverRobot.coordinate
        when (roverRobot.orientation) {
            OrientationType.NORTH -> roverRobot.coordinate.y = y + 1
            OrientationType.SOUTH -> roverRobot.coordinate.y = y - 1
            OrientationType.EAST -> roverRobot.coordinate.x = x + 1
            OrientationType.WEST -> roverRobot.coordinate.x = x - 1
        }
        return roverRobot
    }

    private fun rotate(roverRobot: RoverRobot, command: CommandType): RoverRobot {
        when (command) {
            CommandType.LEFT -> roverRobot.orientation = OrientationUtils.moveLeft(roverRobot.orientation)
            CommandType.RIGHT -> roverRobot.orientation = OrientationUtils.moveRight(roverRobot.orientation)
            else -> {}
        }
        return roverRobot
    }
}
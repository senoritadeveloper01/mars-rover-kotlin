package com.nils.marsrover.service

import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau
import com.nils.marsrover.model.RoverRobot
import com.nils.marsrover.model.type.CommandType
import com.nils.marsrover.model.type.OrientationType
import org.springframework.stereotype.Service

/**
 * Solver class
 * @author  Nil Seri
 */
@Service
class MarsRoverControllerService(val inputValidationService: InputValidationService,
                                 val plateauService: PlateauService,
                                 val roverRobotService: RoverRobotService) {

    fun solve(dimensionX: Int, dimensionY: Int, x: Int, y: Int, roverOrientation: Char, commands: String): RoverRobot {
        inputValidationService.validatePlateau(dimensionX, dimensionY)
        inputValidationService.validateInitialState(x, y, roverOrientation)

        val commandArray = commands.toCharArray()
        inputValidationService.validateCommands(commandArray)

        val orientation = (OrientationType.getOrientationByKey(roverOrientation))!!
        val commandList = commandArray.map(CommandType::getCommandByKey)

        val dimension = Coordinate(dimensionX, dimensionY)
        val plateau: Plateau = plateauService.create(dimension)

        val coordinate = Coordinate(x, y)
        inputValidationService.validateIfCoordinateIsUnoccupied(plateau, coordinate)

        var roverRobot: RoverRobot = roverRobotService.initializeRoverRobot(coordinate, orientation)

        roverRobot = fulfillCommands(plateau, roverRobot, commandList)

        // mark final rover robot's position as an obstacle for the next rover robot
        plateau.roverRobots.add(roverRobot)
        return roverRobot
    }

    private fun fulfillCommands(plateau: Plateau, roverRobot: RoverRobot, commands: List<CommandType>): RoverRobot {
        var movingRoverRobot = roverRobot;
        for (command in commands) {
            movingRoverRobot = roverRobotService.takeAction(plateau, roverRobot, command)
        }
        return movingRoverRobot
    }
}
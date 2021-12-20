package com.nils.marsrover.utils

import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau

/**
 * Util class handling position related functions
 * @author  Nil Seri
 */
class PositionUtils {
    companion object {
        fun isOccupied(plateau: Plateau, coordinate: Coordinate): Boolean {
            for (roverRobot in plateau.roverRobots) {
                if (roverRobot.isOnCoordinate(coordinate)) {
                    return true
                }
            }
            return false
        }
    }
}
package com.nils.marsrover.model

import com.nils.marsrover.model.type.OrientationType

/**
 * Keeps rover robot information, coordinates (x,y) and orientation
 * @author  Nil Seri
 */
class RoverRobot(val coordinate: Coordinate, var orientation:OrientationType) {

    /**
     * Checks if rover robot exists on the given coordinate
     */
    fun isOnCoordinate(targetCoordinate: Coordinate) = coordinate == targetCoordinate;

}
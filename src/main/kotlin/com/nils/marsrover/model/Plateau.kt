package com.nils.marsrover.model

/**
 * Keeps plateau information, coordinates (x,y) and existing non-moving roverRobots
 * @author  Nil Seri
 */
data class Plateau(val dimension: Coordinate, val roverRobots: MutableList<RoverRobot>) {

}
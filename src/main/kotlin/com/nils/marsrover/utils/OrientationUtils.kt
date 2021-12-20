package com.nils.marsrover.utils

import com.nils.marsrover.model.type.OrientationType


/**
 * Util class handling orientation related functions
 * @author  Nil Seri
 */
class OrientationUtils {
    companion object {
        fun moveLeft(orientation: OrientationType): OrientationType {
            return when (orientation) {
                OrientationType.NORTH -> OrientationType.WEST
                OrientationType.WEST -> OrientationType.SOUTH
                OrientationType.EAST -> OrientationType.NORTH
                OrientationType.SOUTH -> OrientationType.EAST
            }
        }

        fun moveRight(orientation: OrientationType): OrientationType {
            return when (orientation) {
                OrientationType.NORTH -> OrientationType.EAST
                OrientationType.WEST -> OrientationType.NORTH
                OrientationType.EAST -> OrientationType.SOUTH
                OrientationType.SOUTH -> OrientationType.WEST
            }
        }
    }
}
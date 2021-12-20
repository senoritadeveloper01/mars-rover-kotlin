package com.nils.marsrover.model.type

/**
 * Enum class for orientation (North, South, East, West)
 * @author  Nil Seri
 */
enum class OrientationType(val key: Char) {

    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W');

    companion object {
        fun getOrientationByKey(key: Char): OrientationType? = values().first { x -> x.key == key }
    }
}
package com.nils.marsrover.model

/**
 * Keeps coordinate information (x,y)
 * @author  Nil Seri
 */
data class Coordinate(var x: Int, var y: Int) {

    override fun toString(): String = "$x $y"
}
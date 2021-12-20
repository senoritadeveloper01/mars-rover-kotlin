package com.nils.marsrover.model.error

/**
 * Project specific error codes
 * @author  Nil Seri
 */
enum class MarsRoverErrorCode(val message: String) {

    PLATEAU_COORDINATES_CANNOT_BE_NEGATIVE("Plateau coordinates cannot be negative, x: %d, y: %d"),
    INITIAL_COORDINATES_CANNOT_BE_NEGATIVE("Initial coordinates cannot be negative, x: %d, y: %d"),
    INVALID_ORIENTATION("Invalid orientation (possible values include 'N'-'S'-'E'-'W'), orientation: %c"),
    INVALID_COMMAND("Invalid command (possible values include 'L'-'R'-'M'), command : %c"),
    POSITION_IS_OCCUPIED("Position is occupied by previously moved rover robot: x: %d, y: %d");
}
package com.nils.marsrover.model.type

/**
 * Enum class for direction commands (Left, Right, Move)
 * @author  Nil Seri
 */
enum class CommandType(val key: Char) {

    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    companion object {
        fun getCommandByKey(key: Char): CommandType = values().first { x -> x.key == key }
    }
}
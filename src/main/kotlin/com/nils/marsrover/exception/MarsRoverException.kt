package com.nils.marsrover.exception

import com.nils.marsrover.model.error.MarsRoverErrorCode
import kotlinx.serialization.*

/**
 * Handles project specific exceptions
 * @author  Nil Seri
 */
@Serializable
class MarsRoverException : RuntimeException {

    constructor(errorCode: MarsRoverErrorCode, cause: Throwable?, vararg args: Any?) : super(errorCode.name + " - " + String.format(errorCode.message, *args), cause)
    constructor(errorCode: MarsRoverErrorCode, vararg args: Any?) : super(errorCode.name + " - " + String.format(errorCode.message, *args))
}
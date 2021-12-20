package com.nils.marsrover.runner

import com.nils.marsrover.model.RoverRobot
import com.nils.marsrover.service.MarsRoverControllerService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * Command line interaction and solver method is handled here
 * @author  Nil Seri
 */
@Component
class MarsRoverCommandLineRunner(val marsRoverControllerService: MarsRoverControllerService): CommandLineRunner {

    override fun run(vararg args: String?) {
        val x = readLine()!!.toInt()
        val y = readLine()!!.toInt()

        var roverRobot: RoverRobot
        while (true) {
            val roverX: Int = readLine()!!.toInt()
            val roverY: Int = readLine()!!.toInt()
            val roverOrientation: Char = readLine()!![0]
            val instructions: String = readLine()!!
            roverRobot = marsRoverControllerService.solve(x, y, roverX, roverY, roverOrientation, instructions)

            println(
                StringBuilder().apply {
                append(roverRobot.coordinate.toString())
                append(' ')
                append(roverRobot.orientation)
            })
        }
    }
}
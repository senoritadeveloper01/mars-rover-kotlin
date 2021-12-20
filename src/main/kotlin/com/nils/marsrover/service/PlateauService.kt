package com.nils.marsrover.service

import com.nils.marsrover.model.Coordinate
import com.nils.marsrover.model.Plateau
import org.springframework.stereotype.Service

/**
 * Class handling plateau related functions
 * @author  Nil Seri
 */
@Service
class PlateauService {

    fun create(dimension: Coordinate): Plateau {
        return Plateau(dimension, mutableListOf())
    }
}
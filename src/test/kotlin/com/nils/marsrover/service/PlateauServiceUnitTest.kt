package com.nils.marsrover.service

import com.nils.marsrover.model.Coordinate
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit5.MockKExtension
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ExtendWith(MockKExtension::class)
internal class PlateauServiceUnitTest {

    @InjectMockKs
    var plateauService = PlateauService()

    @BeforeEach
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun test_plateau_init() {
        val dimension = Coordinate(3, 5)
        val plateau = plateauService.create(dimension)

        Assert.assertTrue("Unexpected plateau dimension X value", plateau.dimension.x == 3)
        Assert.assertTrue("Unexpected plateau dimension Y value", plateau.dimension.y == 5)
        Assert.assertTrue("Unexpected rover robots list size", plateau.roverRobots.isEmpty())
    }

}
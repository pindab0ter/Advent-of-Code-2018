package nl.pindab0ter.aoc2018.day09

import nl.pindab0ter.common.helpers.assertAllEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2018 Day09 - Marble Mania")
class MarbleManiaKtTest {

    @Test
    fun `Calculate the high score`() = assertAllEquals(
        MarbleMania(9, 25).play() to 32,
        MarbleMania(10, 1618).play() to 8317,
        MarbleMania(13, 7999).play() to 146373,
        MarbleMania(17, 1104).play() to 2764,
        MarbleMania(21, 6111).play() to 54718,
        MarbleMania(30, 5807).play() to 37305
    )

}
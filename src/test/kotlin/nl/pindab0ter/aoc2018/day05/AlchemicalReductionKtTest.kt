package nl.pindab0ter.aoc2018.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AlchemicalReductionKtTest {

    private val input = "dabAcCaCBAcCcaDA"

    @Test
    fun reduce() {
        val actual = reduce(input)
        Assertions.assertEquals("dabCBAcaDA", actual)
    }

    @Test
    fun shortestPolymer() {
        val actual = shortestPolymer(input)
        Assertions.assertEquals(4, actual)
    }
}
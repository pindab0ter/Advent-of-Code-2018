package nl.pindab0ter.aoc2018.day05

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class AlchemicalReductionTest {

    private val input = "dabAcCaCBAcCcaDA"

    @Test
    fun reduce() {
        val actual = reduce(input)
        assertEquals("dabCBAcaDA", actual)
    }

    @Test
    fun shortestPolymer() {
        val actual = shortestPolymer(input)
        assertEquals(4, actual)
    }
}

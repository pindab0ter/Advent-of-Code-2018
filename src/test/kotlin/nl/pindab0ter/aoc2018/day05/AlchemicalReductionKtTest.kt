package nl.pindab0ter.aoc2018.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2018 Day 05 - Alchemical Reduction")
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
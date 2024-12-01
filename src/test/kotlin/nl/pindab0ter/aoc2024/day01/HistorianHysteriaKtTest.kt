package nl.pindab0ter.aoc2024.day01

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("2024 Day 01 - Historian Hysteria")
class HistorianHysteriaKtTest {
    @Test
    fun `Get the total distance between two lists`() {
        assertEquals(
            expected = 11,
            actual = listOf(
                listOf(3, 4, 2, 1, 3, 3),
                listOf(4, 3, 5, 3, 9, 3)
            ).totalDistance()
        )
    }

    @Test
    fun `Get the similarity score for two lists`() {
        assertEquals(
            expected = 31,
            actual = listOf(
                listOf(3, 4, 2, 1, 3, 3),
                listOf(4, 3, 5, 3, 9, 3)
            ).similarityScore()
        )
    }
}

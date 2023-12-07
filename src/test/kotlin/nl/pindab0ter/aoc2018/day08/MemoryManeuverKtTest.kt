package nl.pindab0ter.aoc2018.day08

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2018 Day08 - Memory Maneuver")
class MemoryManeuverKtTest {

    private val input = listOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)

    @Test
    fun dataSum() {
        val actual = Node(input.iterator()).dataSum
        Assertions.assertEquals(138, actual)
    }

    @Test
    fun value() {
        val actual = Node(input.iterator()).value
        Assertions.assertEquals(66, actual)
    }
}
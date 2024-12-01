package nl.pindab0ter.aoc2024.day01

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("2024 Day 01 - Historian Hysteria")
class HistorianHysteriaKtTest {

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("pairProvider")
    fun `Get the distance between two points`(expected: Int, input: Pair<Int, Int>) {
        assertEquals(expected, input.distance())
    }

    @Test
    fun `Get the total distance between two lists`() {
        assertEquals(
            expected = 11,
            actual = listOf(
                Pair(1, 3),
                Pair(2, 3),
                Pair(3, 3),
                Pair(3, 4),
                Pair(3, 5),
                Pair(4, 9),
            ).totalDistance()
        )
    }

    companion object {
        @JvmStatic
        fun pairProvider(): Stream<Arguments> = Stream.of(
            arguments(2, Pair(1, 3)),
            arguments(1, Pair(2, 3)),
            arguments(0, Pair(3, 3)),
            arguments(1, Pair(3, 4)),
            arguments(2, Pair(3, 5)),
            arguments(5, Pair(4, 9)),
        )
    }
}

package nl.pindab0ter.aoc2019.day01

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2019 Day 01 - The Tyranny of the Rocket Equation")
class TheTyrannyOfTheRocketEquationKtTest {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("partOneProvider")
    fun `Part one`(expected: Int, input: Int) {
        val actual = fuelRequired(input)

        assertEquals(expected, actual)
    }

    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("partTwoProvider")
    fun `Part two`(expected: Int, input: Int) {
        val actual = totalFuelRequired(input)

        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun partOneProvider(): Stream<Arguments> = Stream.of(
            arguments(2, 12),
            arguments(2, 14),
            arguments(654, 1969),
            arguments(33583, 100756),
        )

        @JvmStatic
        fun partTwoProvider(): Stream<Arguments> = Stream.of(
            arguments(2, 14),
            arguments(966, 1969),
            arguments(50346, 100756),
        )
    }
}

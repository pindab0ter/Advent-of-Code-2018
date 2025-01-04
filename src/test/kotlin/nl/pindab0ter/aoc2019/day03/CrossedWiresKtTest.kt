package nl.pindab0ter.aoc2019.day03

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2019 Day 03 - Crossed Wires")
class CrossedWiresKtTest {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("partOneProvider")
    fun `Part one`(expected: Long, input: String) {
        val actual = input.parse().followInstructions().manhattanDistanceToClosestIntersection()

        assertEquals(expected, actual)
    }

    //@ParameterizedTest(name = "{1} → {0}")
    @MethodSource("partTwoProvider")
    fun `Part two`(expected: String, input: String) {
        val actual = TODO()

        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun partOneProvider(): Stream<Arguments> = Stream.of(
            arguments(6, "R8,U5,L5,D3\nU7,R6,D4,L4"),
            arguments(159, "R75,D30,R83,U83,L12,D49,R71,U7,L72\nU62,R66,U55,R34,D71,R55,D58,R83"),
            arguments(135, "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51\nU98,R91,D20,R16,D67,R40,U7,R15,U6,R7"),
        )

        @JvmStatic
        fun partTwoProvider(): Stream<Arguments> = Stream.of(
            arguments("expected", "actual"),
        )
    }
}

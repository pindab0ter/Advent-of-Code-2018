package nl.pindab0ter.aoc2015.day01

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2015 Day 01 - Not Quite Lisp")
class NotQuiteLispKtTest {

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("instructionsProvider")
    fun `Calculate the floor number`(actual: Int, input: String) {
        assertEquals(actual, calculateFinalFloor(input))
    }

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("expectedFloorProvider")
    fun `Calculate the instruction position leading into the basement`(actual: Int, input: String) {
        assertEquals(actual, findInstructionLeadingToBasement(input))
    }

    companion object {
        @JvmStatic
        fun instructionsProvider(): Stream<Arguments> = Stream.of(
            arguments(0, "(())"),
            arguments(0, "()()"),
            arguments(3, "((("),
            arguments(3, "(()(()("),
            arguments(3, "))((((("),
            arguments(-1, "())"),
            arguments(-1, "))("),
            arguments(-3, ")))"),
            arguments(-3, ")())())"),
        )

        @JvmStatic
        fun expectedFloorProvider(): Stream<Arguments> = Stream.of(
            arguments(1, ")"),
            arguments(5, "()())"),
        )
    }
}
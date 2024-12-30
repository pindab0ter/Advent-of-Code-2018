package nl.pindab0ter.aoc2019.day02

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@Suppress("ClassName")
@DisplayName("2019 Day 02 - 1202 Program Alarm")
class `1202ProgramAlarmKtTest` {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("partOneProvider")
    fun `Part one`(expected: Memory, input: Memory) {
        val computer = IntcodeComputer(input)
        assertEquals(expected, computer.run())
    }

    //    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("partTwoProvider")
    fun `Part two`(expected: String, input: String) {
        val actual = TODO()

        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun partOneProvider(): Stream<Arguments> = Stream.of(
            arguments(listOf(2, 0, 0, 0, 99), listOf(1, 0, 0, 0, 99)), // 1 + 1 = 2
            arguments(listOf(2, 3, 0, 6, 99), listOf(2, 3, 0, 3, 99)), // 3 * 2 = 6
            arguments(listOf(2, 4, 4, 5, 99, 9801), listOf(2, 4, 4, 5, 99, 0)), // 99 * 99 = 9801
            arguments(listOf(30, 1, 1, 4, 2, 5, 6, 0, 99), listOf(1, 1, 1, 4, 99, 5, 6, 0, 99)),
        )

        @JvmStatic
        fun partTwoProvider(): Stream<Arguments> = Stream.of(
            arguments("expected", "actual"),
        )
    }
}

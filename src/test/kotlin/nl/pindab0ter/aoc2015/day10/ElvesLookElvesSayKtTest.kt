package nl.pindab0ter.aoc2015.day10

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.of
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("2015 Day 10: Elves Look, Elves Say")
class ElvesLookElvesSayTest {

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("lookAndSayProvider")
    fun `Test look-and-say function`(expected: List<Int>, input: List<Int>) {
        assertEquals(expected, lookAndSay(input))
    }

    companion object {
        @JvmStatic
        fun lookAndSayProvider(): Stream<Arguments> = Stream.of(
            of(listOf(1, 1), listOf(1)),
            of(listOf(2, 1), listOf(1, 1)),
            of(listOf(1, 2, 1, 1), listOf(2, 1)),
            of(listOf(3, 1, 2, 2, 1, 1), listOf(1, 1, 1, 2, 2, 1))
        )
    }
}
package nl.pindab0ter.aoc2015.day08

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@DisplayName("2015 Day 8: Matchsticks")
class MatchsticksKtTest {

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("parseProvider")
    fun `Test parse function`(expected: Int, input: String) {
        assertEquals(expected, parse(input).length)
    }

    companion object {
        @JvmStatic
        fun parseProvider(): Stream<Arguments> = Stream.of(
            Arguments.of(0, """"""""),         // ""
            Arguments.of(3, """"abc""""),      // abc
            Arguments.of(7, """"aaa\"aaa""""), // aaa"aaa
            Arguments.of(1, """"\x27""""),     // '
            Arguments.of(1, """"\x66""""),     // f
            Arguments.of(4, """"\\x66""""),    // \x66
            Arguments.of(2, """"\\\x66""""),   // \f
            Arguments.of(5, """"\\\\x66""""),  // \\x66
        )
    }
}
package nl.pindab0ter.aoc2015.day07

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2015 Day 07 - Some Assembly Required")
class SomeAssemblyRequiredKtTest {

    @ParameterizedTest(name = "{1} -> {0}")
    @MethodSource("instructionsProvider")
    fun `Determine the signal on the wire`(actual: Int, wire: String) {
        val input = """
            123 -> x
            456 -> y
            x AND y -> d
            x OR y -> e
            x LSHIFT 2 -> f
            y RSHIFT 2 -> g
            NOT x -> h
            NOT y -> i
        """.trimIndent()

        assertEquals(actual.toUShort(), parse(input).getSignal(wire))
    }

    companion object {
        @JvmStatic
        fun instructionsProvider(): Stream<Arguments> = Stream.of(
            arguments(72, "d"),
            arguments(507, "e"),
            arguments(492, "f"),
            arguments(114, "g"),
            arguments(65412, "h"),
            arguments(65079, "i"),
            arguments(123, "x"),
            arguments(456, "y"),
        )
    }
}
package nl.pindab0ter.aoc2024.day03

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2024 Day 03 - Mull It Over")
class MullItOverKtTest {
    @Test
    fun `Find all multiplication instructions`() {
        val input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
        val expected = listOf(2 to 4, 5 to 5, 11 to 8, 8 to 5)
        val actual = input.findInstructions()

        assertEquals(expected, actual)
    }

    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("memoryProvider")
    fun `Find all multiplication instructions when ignoring disabled instructions`(
        expected: List<Multiplication>,
        input: String,
    ) {
        val actual = input.removeDisabledInstructions().findInstructions()

        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        fun memoryProvider(): Stream<Arguments> = Stream.of(
            // Provided example. Note that it is different from the provided example for part 1.
            arguments(
                listOf(2 to 4, 8 to 5),
                "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
            ),

            // No closing `do` before end
            arguments(listOf<Multiplication>(), "don't()mul(1,1)"),
            // Newline between `don't` and `do`
            arguments(listOf<Multiplication>(), "don't()\nmul(1,1)do()"),
        )
    }
}

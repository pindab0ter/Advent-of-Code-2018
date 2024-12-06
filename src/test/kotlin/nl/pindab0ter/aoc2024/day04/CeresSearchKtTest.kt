package nl.pindab0ter.aoc2024.day04

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2024 Day 04 - Ceres Search")
class CeresSearchKtTest {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("wordSearchProvider")
    fun `Count appearances of 'XMAS' per row`(expected: Int, input: String) {
        assertEquals(expected, input.lines().map(String::toList).countXmasOccurrences())
    }

    companion object {
        @JvmStatic
        fun wordSearchProvider(): Stream<Arguments> = Stream.of(
            arguments(1, "XMAS"),   // ✔︎
            arguments(1, ".XMAS."), // ✔︎
            arguments(1, "MXMASS"), // ✔︎
            arguments(1, ".SAMX."), // ✔︎
            arguments(1, "SSAMXM"), // ✔︎
            arguments(0, "XMAAS"),  // ❌
            arguments(0, "XMAAS"),  // ❌
            arguments(
                4,
                """
                ..X...
                .SAMX.
                .A..A.
                XMAS.S
                .X....
                """.trimIndent()
            ),
            arguments(
                18,
                """
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """.trimIndent()
            )
        )
    }
}

package nl.pindab0ter.aoc2024.day02

import nl.pindab0ter.aoc2024.day02.Status.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2024 Day 02 - Red-Nosed Reports")
class RedNosedReportsKtTest {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("levelsProvider")
    fun `Determine the status of two levels`(expected: Status, levels: Pair<Level, Level>) =
        assertEquals(expected, levels.status())

    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("reportsProvider")
    fun `Determine the report status`(expected: Status, report: Report) =
        assertEquals(expected, report.status())

    companion object {
        @JvmStatic
        fun levelsProvider(): Stream<Arguments> = Stream.of(
            arguments(UNSAFE, Pair(1, 1)),

            arguments(DECREASING, Pair(5, 4)),
            arguments(DECREASING, Pair(5, 2)),
            arguments(UNSAFE, Pair(5, 1)),

            arguments(INCREASING, Pair(1, 2)),
            arguments(INCREASING, Pair(1, 4)),
            arguments(UNSAFE, Pair(1, 5)),
        )

        @JvmStatic
        fun reportsProvider(): Stream<Arguments> = Stream.of(
            arguments(DECREASING, listOf(7, 6, 4, 2, 1)),
            arguments(UNSAFE, listOf(1, 2, 7, 8, 9)),
            arguments(UNSAFE, listOf(9, 7, 6, 2, 1)),
            arguments(UNSAFE, listOf(1, 3, 2, 4, 5)),
            arguments(UNSAFE, listOf(8, 6, 4, 4, 1)),
            arguments(INCREASING, listOf(1, 3, 6, 7, 9))
        )
    }
}

package nl.pindab0ter.aoc2023.day12

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2023 Day 12 - Hot Springs")
class HotSpringsKtTest {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("arrangementCountProvider")
    fun `Count possible arrangements`(expected: Long, input: String) {
        val record = parse(input).first()
        assertEquals(expected, record.countArrangements())
    }

    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("unfoldedRecordProvider")
    fun `Unfold the record`(expectedInput: String, input: String) {
        val expected = parse(expectedInput).first()
        val actual = parse(input).first().unfold()

        assertEquals(expected, actual)
    }

    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("unfoldedArrangementCountProvider")
    fun `Count possible arrangements after unfolding`(expected: Long, input: String) {
        val record = parse(input).first().unfold()
        assertEquals(expected, record.countArrangements())
    }

    companion object {
        @JvmStatic
        fun arrangementCountProvider(): Stream<Arguments> = Stream.of(
            arguments(1, "# 1"),
            arguments(1, "#. 1"),
            arguments(1, "? 1"),
            arguments(1, "## 2"),
            arguments(1, "#? 2"),
            arguments(1, "?# 2"),
            arguments(2, "?? 1"),
            arguments(1, "### 3"),
            arguments(1, "??? 3"),
            arguments(1, "??? 1,1"),
            arguments(2, "??? 2"),
            arguments(1, "###.??? 3,1,1"),

            // Provided test inputs
            arguments(1, "#.#.### 1,1,3"),
            arguments(1, ".#...#....###. 1,1,3"),
            arguments(1, ".#.###.#.###### 1,3,1,6"),
            arguments(1, "####.#...#... 4,1,1"),
            arguments(1, "#....######..#####. 1,6,5"),
            arguments(1, ".###.##....# 3,2,1"),
            arguments(1, "???.### 1,1,3"),
            arguments(4, ".??..??...?##. 1,1,3"),
            arguments(1, "?#?#?#?#?#?#?#? 1,3,1,6"),
            arguments(1, "????.#...#... 4,1,1"),
            arguments(4, "????.######..#####. 1,6,5"),
            arguments(10, "?###???????? 3,2,1"),
        )

        @JvmStatic
        fun unfoldedRecordProvider(): Stream<Arguments> = Stream.of(
            arguments(".#?.#?.#?.#?.# 1,1,1,1,1", ".# 1"),
            arguments("???.###????.###????.###????.###????.### 1,1,3,1,1,3,1,1,3,1,1,3,1,1,3", "???.### 1,1,3"),
        )


        @JvmStatic
        fun unfoldedArrangementCountProvider(): Stream<Arguments> = Stream.of(
            arguments(1, "???.### 1,1,3"),
            arguments(16384, ".??..??...?##. 1,1,3"),
            arguments(1, "?#?#?#?#?#?#?#? 1,3,1,6"),
            arguments(16, "????.#...#... 4,1,1"),
            arguments(2500, "????.######..#####. 1,6,5"),
            arguments(506250, "?###???????? 3,2,1"),
        )
    }
}
package nl.pindab0ter.aoc2023.day12

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@DisplayName("2023 Day 12: Hot Springs")
class HotSpringsKtTest {
    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("arrangementCountProvider")
    fun `Count possible arrangements`(expected: Long, input: String) {
        assertEquals(expected, parse(input)[0].countArrangements())
    }

    companion object {
        @JvmStatic
        fun arrangementCountProvider(): Stream<Arguments> = Stream.of(
            arguments(1, "###.??? 3,1,1"),
            arguments(1, "???.### 1,1,3"),
            arguments(4, ".??..??...?##. 1,1,3"),
            arguments(1, "?#?#?#?#?#?#?#? 1,3,1,6"),
            arguments(1, "????.#...#... 4,1,1"),
            arguments(4, "????.######..#####. 1,6,5"),
            arguments(1, "?###???????? 3,2,1"),
        )
    }
}
package nl.pindab0ter.aoc2023.day01

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@Suppress("SpellCheckingInspection")
@DisplayName("2023 Day 01 - Trebuchet?!")
internal class TrebuchetKtTest {

    @ParameterizedTest(name = "{1} → {0}")
    @MethodSource("calibrationValueProvider")
    fun `Get the calibration value`(expected: Int, input: String) {
        assertEquals(expected, getCalibrationValue(input))
    }

    @Test
    fun `Sum of all part one`() {
        val result = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines().sumOf(::getCalibrationValue)
        Assertions.assertEquals(result, 142)
    }

    @Test
    fun `Sum of all part two`() {
        val result = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent().lines().sumOf(::getCalibrationValue)
        Assertions.assertEquals(result, 281)
    }

    companion object {
        @JvmStatic
        fun calibrationValueProvider(): Stream<Arguments> = Stream.of(
            arguments(12, "1abc2"),
            arguments(38, "pqr3stu8vwx"),
            arguments(15, "a1b2c3d4e5f"),
            arguments(77, "treb7uchet"),
            arguments(29, "two1nine"),
            arguments(83, "eightwothree"),
            arguments(13, "abcone2threexyz"),
            arguments(24, "xtwone3four"),
            arguments(42, "4nineeightseven2"),
            arguments(14, "zoneight234"),
            arguments(76, "7pqrstsixteen"),

            // The following cases are not specified in the assignment, but are present in the input.
            arguments(77, "7g"),
            arguments(83, "eighthree"),
            arguments(45, "45"),
        )
    }
}

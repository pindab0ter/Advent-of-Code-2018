package nl.pindab0ter.aoc2023.day01

import nl.pindab0ter.common.assertAllEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
@DisplayName("2023 Day 01 - Trebuchet?!")
internal class TrebuchetKtTest {

    @Test
    fun `Get the calibration value`() = assertAllEquals(
        12 to getCalibrationValue("1abc2"),
        38 to getCalibrationValue("pqr3stu8vwx"),
        15 to getCalibrationValue("a1b2c3d4e5f"),
        77 to getCalibrationValue("treb7uchet"),
        29 to getCalibrationValue("two1nine"),
        83 to getCalibrationValue("eightwothree"),
        13 to getCalibrationValue("abcone2threexyz"),
        24 to getCalibrationValue("xtwone3four"),
        42 to getCalibrationValue("4nineeightseven2"),
        14 to getCalibrationValue("zoneight234"),
        76 to getCalibrationValue("7pqrstsixteen"),

        // The following cases are not specified in the assignment, but are present in the input.
        77 to getCalibrationValue("7g"),
        83 to getCalibrationValue("eighthree"),
        45 to getCalibrationValue("45"),
    )

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
}
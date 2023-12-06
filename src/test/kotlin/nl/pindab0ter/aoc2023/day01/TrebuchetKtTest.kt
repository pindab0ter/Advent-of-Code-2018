package nl.pindab0ter.aoc2023.day01

import nl.pindab0ter.common.helpers.assertAllEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@Suppress("SpellCheckingInspection")
internal class TrebuchetKtTest {

    @Test
    fun `Get the calibration value`() = assertAllEquals(
        getCalibrationValue("1abc2") to 12,
        getCalibrationValue("pqr3stu8vwx") to 38,
        getCalibrationValue("a1b2c3d4e5f") to 15,
        getCalibrationValue("treb7uchet") to 77,
        getCalibrationValue("two1nine") to 29,
        getCalibrationValue("eightwothree") to 83,
        getCalibrationValue("abcone2threexyz") to 13,
        getCalibrationValue("xtwone3four") to 24,
        getCalibrationValue("4nineeightseven2") to 42,
        getCalibrationValue("zoneight234") to 14,
        getCalibrationValue("7pqrstsixteen") to 76,

        // The following cases are not specified in the assignment, but are present in the input.
        getCalibrationValue("7g") to 77,
        getCalibrationValue("eighthree") to 83,
        getCalibrationValue("45") to 45,
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
    fun `sum of all part two`() {
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
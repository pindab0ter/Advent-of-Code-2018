package nl.pindab0ter.aoc2023.day01

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class TrebuchetKtTest {

    //////////////
    // Part One //
    //////////////

    @Test
    fun `1abc2`() {
        val input = "1abc2"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 12)
    }

    @Test
    fun `pqr3stu8vwx`() {
        val input = "pqr3stu8vwx"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 38)
    }

    @Test
    fun `a1b2c3d4e5f`() {
        val input = "a1b2c3d4e5f"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 15)
    }

    @Test
    fun `treb7uchet`() {
        val input = "treb7uchet"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 77)
    }

    @Test
    fun `sum of all part one`() {
        val result = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines().sumOf(::getCalibrationValue)
        Assertions.assertEquals(result, 142)
    }

    //////////////
    // Part Two //
    //////////////

    @Test
    fun `two1nine`() {
        val input = "two1nine"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 29)
    }

    @Test
    fun `eightwothree`() {
        val input = "eightwothree"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 83)
    }

    @Test
    fun `abcone2threexyz`() {
        val input = "abcone2threexyz"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 13)
    }

    @Test
    fun `xtwone3four`() {
        val input = "xtwone3four"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 24)
    }

    @Test
    fun `4nineeightseven2`() {
        val input = "4nineeightseven2"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 42)
    }

    @Test
    fun `zoneight234`() {
        val input = "zoneight234"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 14)
    }

    @Test
    fun `7pqrstsixteen`() {
        val input = "7pqrstsixteen"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 76)
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

    ////////////
    // Custom //
    ////////////

    @Test
    // This behaviour is not specified in the assignment, but is present in the input.
    fun `7g`() {
        val input = "7g"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 77)
    }

    @Test
    // This behaviour is not covered by the provided test cases, but is present in the input.
    fun `eighthree`() {
        val input = "eighthree"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 83)
    }

    @Test
    fun `45`() {
        val input = "45"
        val result = getCalibrationValue(input)
        Assertions.assertEquals(result, 45)
    }
}
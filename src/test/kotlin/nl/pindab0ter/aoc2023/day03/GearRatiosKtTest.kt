package nl.pindab0ter.aoc2023.day03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2023 Day 03 - Gear Ratios")
internal class GearRatiosKtTest {

    //////////////
    // Part one //
    //////////////

    @Test
    fun `Symbol to the right`() {
        val input = """
            ....
            123*
            ....
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }

    @Test
    fun `Symbol to the left`() {
        val input = """
            ....
            *123
            ....
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }

    @Test
    fun `Symbol to the left and right`() {
        val input = """
            .....
            *123*
            .....
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }


    @Test
    fun `Symbol in between`() {
        val input = """
            .....
            12*34
            .....
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(12, 34), result)
    }

    @Test
    fun `Symbol above first digit`() {
        val input = """
            *..
            123
            ...
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }

    @Test
    fun `Symbol above middle digit`() {
        val input = """
            .*.
            123
            ...
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }

    @Test
    fun `Symbol above last digit`() {
        val input = """
            ..*
            123
            ...
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }

    @Test
    fun `Symbol to the top right of the last digit`() {
        val input = """
            ...*
            123
            ...
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }

    @Test
    fun `Symbol too far from the top right of the last digit`() {
        val input = """
            ....*
            123..
            .....
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf<Int>(), result)
    }

    @Test
    fun `Symbol to the top left of the first digit`() {
        val input = """
            *...
            .123
            ....
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(123), result)
    }

    @Test
    fun `Symbol too far from the top left of the first digit`() {
        val input = """
            *....
            ..123
            .....
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf<Int>(), result)
    }

    @Test
    fun `Example input`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent()
        val result = getPartNumbers(input)
        Assertions.assertEquals(listOf(467, 35, 633, 617, 592, 755, 664, 598), result)
    }

    //////////////
    // Part two //
    //////////////

    @Test
    fun `Find all gears`() {
        val input = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..
        """.trimIndent()
        val result = getGearRatios(input)
        Assertions.assertEquals(listOf(16345, 451490), result)
    }

}
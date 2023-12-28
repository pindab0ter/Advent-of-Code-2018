package nl.pindab0ter.aoc2015.day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2015 Day 02 - I Was Told There Would Be No Math")
class IWasToldThereWouldBeNoMathKtTest {
    @Test
    fun `Calculate the required amount of wrapping paper`() {
        val input = """
            2x3x4
            1x1x10
        """.trimIndent()

        val boxes = parse(input)

        assertEquals(58 + 43, boxes.sumOf(Box::requiredWrappingPaper))
    }
}
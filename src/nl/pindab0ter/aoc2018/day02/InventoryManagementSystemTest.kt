package nl.pindab0ter.aoc2018.day02

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class InventoryManagementSystemTest {
    @Test
    fun boxCount() {
        val input = listOf("abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab")
        val actual = boxCount(input)
        assertEquals(12, actual)
    }

    @Test
    fun findBoxes() {
        val input = listOf("abcde", "fghij", "klmno", "pqrst", "fguij", "axcye", "wvxyz")
        val actual = findBoxes(input)
        assertEquals("fgij", actual)
    }
}

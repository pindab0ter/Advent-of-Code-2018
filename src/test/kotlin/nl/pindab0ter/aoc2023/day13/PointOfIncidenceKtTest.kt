package nl.pindab0ter.aoc2023.day13

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2023 Day 13: Point of Incidence")
class PointOfIncidenceKtTest {
    @Test
    fun `Finds the correct reflection lines`() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent()

        val patterns = parse(input)

        // Problem description counts from 1, so actual values are raised by 1
        assertEquals(5, getSymmetryAxisIndex(patterns[0].columns)?.plus(1))
        assertEquals(null, getSymmetryAxisIndex(patterns[0].rows)?.plus(1))

        assertEquals(null, getSymmetryAxisIndex(patterns[1].columns)?.plus(1))
        assertEquals(4, getSymmetryAxisIndex(patterns[1].rows)?.plus(1))
    }
}

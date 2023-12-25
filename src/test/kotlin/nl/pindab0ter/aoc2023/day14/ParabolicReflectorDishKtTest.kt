package nl.pindab0ter.aoc2023.day14

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

@DisplayName("2023 Day 14: Parabolic Reflector Dish")
class ParabolicReflectorDishKtTest {
    @Test
    fun `Calculate the load on the north support beams`() {
        val input = """
            OOOO.#.O..
            OO..#....#
            OO..O##..O
            O..#.OO...
            ........#.
            ..#....#.#
            ..O..#.O.O
            ..O.......
            #....###..
            #....#....
        """.trimIndent()

        val rocks = parse(input)

        assertEquals(136, calculateLoad(rocks))
    }
}
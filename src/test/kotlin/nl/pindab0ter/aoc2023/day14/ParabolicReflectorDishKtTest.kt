package nl.pindab0ter.aoc2023.day14

import nl.pindab0ter.aoc2023.day14.Direction.NORTH
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

        assertEquals(136, calculateLoad(rocks.toSortedSet(NORTH.comparator)))
    }

    @Test
    fun `Move the round stones when tilting`() {
        val input = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent()
        val expectedInput = """
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
        val expected = parse(expectedInput)

        assertEquals(expected, tilt(rocks, NORTH))
    }

    @Test
    fun `Perform a tilt cycle`() {
        val initialState = """
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent()
        val afterFirstCycle = """
            .....#....
            ....#...O#
            ...OO##...
            .OO#......
            .....OOO#.
            .O#...O#.#
            ....O#....
            ......OOOO
            #...O###..
            #..OO#....
        """.trimIndent()
        val afterSecondCycle = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #..OO###..
            #.OOO#...O
        """.trimIndent()
        val afterThirdCycle = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #...O###.O
            #.OOO#...O
        """.trimIndent()

        val rocks = parse(initialState)
        val expectedAfterFirstCycle = parse(afterFirstCycle)
        val expectedAfterSecondCycle = parse(afterSecondCycle)
        val expectedAfterThirdCycle = parse(afterThirdCycle)

        assertEquals(expectedAfterFirstCycle, spinCycle(rocks))
        assertEquals(expectedAfterSecondCycle, spinCycle(spinCycle(rocks)))
        assertEquals(expectedAfterThirdCycle, spinCycle(spinCycle(spinCycle(rocks))))
    }
}
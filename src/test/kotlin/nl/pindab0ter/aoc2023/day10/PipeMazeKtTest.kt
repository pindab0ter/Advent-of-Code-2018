package nl.pindab0ter.aoc2023.day10

import nl.pindab0ter.common.assertAllEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("2023 Day 10 - Pipe Maze")
class PipeMazeKtTest {
    @Test
    fun `Furthest distance from the starting point`() = assertAllEquals(
        4 to furthestDistanceFromStart(
            Maze.from(
                """
                .....
                .S-7.
                .|.|.
                .L-J.
                .....
                """.trimIndent()
            )
        ),
        8 to furthestDistanceFromStart(
            Maze.from(
                """
                ..F7.
                .FJ|.
                SJ.L7
                |F--J
                LJ...
                """.trimIndent()
            )
        ),
    )
}
package nl.pindab0ter.day10

import nl.pindab0ter.day10.Pipe.*

fun main() {
    val input = """
        7-F7-
        .FJ|7
        SJLL7
        |F--J
        LJ.LJ
    """.trimIndent()

    val maze = Maze.from(input)

    print(maze)
}

val sections = mapOf(
    '|' to VERTICAL,
    '-' to HORIZONTAL,
    'L' to BOTTOM_LEFT_BEND,
    'J' to BOTTOM_RIGHT_BEND,
    '7' to TOP_RIGHT_BEND,
    'F' to TOP_LEFT_BEND,
)

fun print(maze: Maze) = buildString {
    maze.tiles.forEach { row -> appendLine(row.joinToString("") { it.toString() }) }
}.let(::println)


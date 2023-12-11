package nl.pindab0ter.aoc2023.day10

import nl.pindab0ter.common.getInput

fun main() {
    val input = getInput(2023, 10)

    val maze = Maze.from(input)

    print(maze)

    println("Furthest distance from the start: ${maze.furthestDistanceFromStart}")
    println("Amount of tiles enclosed by the loop: ${maze.tilesEnclosedByLoop.count()}")
}

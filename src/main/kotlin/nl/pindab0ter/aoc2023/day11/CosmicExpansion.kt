package nl.pindab0ter.aoc2023.day11

import nl.pindab0ter.common.*

fun main() {
    val input = getInput(2023, 11)

    val universe = parse(input)
    val expandedUniverse = universe.expand()
    val shortestDistances = expandedUniverse.allCombinations().map { (a, b) -> manhattanDistance(a, b) }

    println("Sum of shortest distances: ${shortestDistances.sum()}")
}

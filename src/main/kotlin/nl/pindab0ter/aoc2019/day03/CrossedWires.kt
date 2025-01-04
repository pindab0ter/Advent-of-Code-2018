package nl.pindab0ter.aoc2019.day03

import nl.pindab0ter.common.*
import nl.pindab0ter.common.Direction.*

fun main() {
    val instructions = getInput(2019, 3).parse()
    val paths = instructions.followInstructions()
    val manhattanDistanceToClosestIntersection = paths.manhattanDistanceToClosestIntersection()

    println("The Manhattan distance to the closest intersection from the central port is $manhattanDistanceToClosestIntersection")
}

fun List<Set<Instruction>>.followInstructions(): List<Set<Coordinate>> = map { instructions ->
    instructions.fold(mutableListOf(Coordinate(0, 0))) { path, instruction ->
        repeat(instruction.steps.toInt()) {
            path.add(path.last().advance(instruction.direction))
        }
        path
    }.toSet()
}

fun List<Set<Coordinate>>.manhattanDistanceToClosestIntersection(): Long? {
    val redWire = first()
    val greenWire = second().toSet()

    return redWire.filter { it in greenWire }
        .minus(Coordinate(0, 0))
        .minOfOrNull { coordinate -> coordinate.manhattanDistance(0, 0) }
}

fun String.parse(): List<Set<Instruction>> = lines().map { line ->
    line.split(",").map { step ->
        val (_, directionString, stepsCountString) = Regex("""(?<direction>[UDLR])(?<steps>\d+)""").find(step)!!.groupValues
        Instruction(directionString.first().toDirection(), stepsCountString.toLong())
    }.toSet()
}

data class Instruction(
    val direction: Direction,
    val steps: Long,
)

fun Char.toDirection() = when (this) {
    'U' -> NORTH
    'D' -> SOUTH
    'L' -> WEST
    'R' -> EAST
    else -> throw IllegalArgumentException("Invalid direction: $this")
}

private fun Coordinate.advance(direction: Direction): Coordinate = when (direction) {
    NORTH -> copy(y = y - 1)
    EAST -> copy(x = x + 1)
    SOUTH -> copy(y = y + 1)
    WEST -> copy(x = x - 1)
}

package nl.pindab0ter.aoc2019.day03

import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal
import nl.pindab0ter.common.*
import nl.pindab0ter.common.Direction.*
import kotlin.time.measureTime
import kotlin.time.measureTimedValue

fun main() {
    val instructions = getInput(2019, 3).parse()

    val (paths, followInstructionsDuration) = measureTimedValue {
        instructions.followInstructions()
    }

    println("Parsing and following instructions took $followInstructionsDuration")

    val (manhattanDistanceToClosestIntersection, manhattanDistanceToClosestIntersectionDuration) = measureTimedValue {
        paths.manhattanDistanceToClosestIntersection()
    }

    println("The Manhattan distance to the closest intersection from the central port is $manhattanDistanceToClosestIntersection")
    println("Calculating the Manhattan distance to the closest intersection took $manhattanDistanceToClosestIntersectionDuration")

    val printDuration = measureTime { paths.print() }

    println("Printing the paths took $printDuration")
}


fun List<List<Instruction>>.followInstructions(): List<List<Coordinate>> = map { instructions ->
    instructions.fold(listOf(Coordinate(0, 0))) { path, instruction ->
        (1..instruction.steps).fold(path) { steps, _ ->
            steps + steps.last().step(instruction.direction)
        }
    }
}

fun List<List<Coordinate>>.manhattanDistanceToClosestIntersection(): Long? = first().filter { second().contains(it) }
    .minus(Coordinate(0, 0))
    .minOfOrNull { coordinate -> coordinate.manhattanDistance(0, 0) }

fun String.parse(): List<List<Instruction>> = lines().map { line ->
    line.split(",").map { step ->
        val (_, directionString, stepsCountString) = Regex("""(?<direction>[UDLR])(?<steps>\d+)""").find(step)!!.groupValues
        Instruction(directionString.first().toDirection(), stepsCountString.toLong())
    }
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

private fun Coordinate.step(direction: Direction): Coordinate = when (direction) {
    NORTH -> copy(y = y - 1)
    EAST -> copy(x = x + 1)
    SOUTH -> copy(y = y + 1)
    WEST -> copy(x = x - 1)
}

private fun List<List<Coordinate>>.print(terminal: Terminal = Terminal()) = buildString {
    val all = flatten()
    val minX = all.minOf { it.x - 1 }
    val minY = all.minOf { it.y - 1 }
    val maxX = all.maxOf { it.x + 1 }
    val maxY = all.maxOf { it.y + 1 }

    (minY..maxY).forEach { y ->
        (minX..maxX).forEach { x ->
            val textStyle = when {
                this@print[0].contains(x, y) && this@print[1].contains(x, y) -> brightYellow
                this@print[0].contains(x, y) -> red
                this@print[1].contains(x, y) -> green
                else -> gray
            }.let { color -> TextStyle(color) }

            val right = Coordinate(x + 1, y)
            val left = Coordinate(x - 1, y)
            val down = Coordinate(x, y + 1)
            val up = Coordinate(x, y - 1)

            val character = when {
                x == 0L && y == 0L -> "○"
                !all.contains(x, y) -> "·"

                all.containsAll(right, down, left, up) -> "┼"
                all.containsAll(right, up, down) -> "├"
                all.containsAll(left, up, down) -> "┤"
                all.containsAll(right, left, down) -> "┬"
                all.containsAll(right, left, up) -> "┴"

                all.containsAll(right, down) -> "╭"
                all.containsAll(left, down) -> "╮"
                all.containsAll(right, up) -> "╰"
                all.containsAll(left, up) -> "╯"
                all.containsAll(up, down) -> "│"
                all.containsAll(left, right) -> "─"
                all.contains(up) -> "╵"
                all.contains(down) -> "╷"
                all.contains(left) -> "╴"
                all.contains(right) -> "╶"
                else -> throw IllegalStateException("Unexpected state")
            }
            append(textStyle(character))
        }
        appendLine()
    }
}.let { string -> terminal.println(string) }

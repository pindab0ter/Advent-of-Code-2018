package nl.pindab0ter.aoc2024.day06

import com.github.ajalt.mordant.animation.textAnimation
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.terminal.Terminal
import kotlinx.coroutines.runBlocking
import nl.pindab0ter.aoc2024.day06.Direction.*
import nl.pindab0ter.aoc2024.day06.Tile.*
import nl.pindab0ter.common.*

/**
 * The animation doesn't work when running through IntelliJ.
 * Use `./gradleW :runKotlin2024day06` instead, or even better, reroute `installDist` to this class by modifying
 * `mainClass.set()` in `application` in `build.gradle.kts` and run the generated binary:
 * `build/install/AdventOfCode/bin/AdventOfCode`.
 */
fun main() = runBlocking {
    val input = getInput(2024, 6)
    val terminal = Terminal()

    val lab = Lab.fromString(input)

    val animation = terminal.textAnimation<Lab> { lab -> lab.toString() }
    animation.update(lab)

    val finalState = generateSequence(lab) { currentLab ->
        if (!currentLab.guardIsInside) return@generateSequence null

        val tileInFrontOfGuard = currentLab.map[currentLab.guard.coordinateInFront()]

        // TODO: Keep doing this if guard is obstructed again after turning
        val movedGuard = if (tileInFrontOfGuard == OBSTRUCTION) {
            val turnedGuard = currentLab.guard.copy(
                direction = currentLab.guard.direction.ninetyDegreesClockwise()
            )
            if (currentLab.map[turnedGuard.coordinateInFront()] == OBSTRUCTION) {
                throw IllegalStateException("Guard is obstructed in another direction")
            }
            turnedGuard.copy(coordinate = turnedGuard.coordinateInFront())
        } else {
            currentLab.guard.copy(coordinate = currentLab.guard.coordinateInFront())
        }

        val updatedMap = currentLab.map.mapIndexed { y, row ->
            row.mapIndexed { x, tile ->
                when {
                    Coordinate(x, y) == currentLab.guard.coordinate -> TRAVELLED
                    Coordinate(x, y) == movedGuard.coordinate -> GUARD
                    else -> tile
                }
            }
        }

        currentLab.copy(
            map = updatedMap,
            guard = movedGuard,
        )
    }.onEach { updatedLab ->
        runBlocking {
            animation.update(updatedLab)
        }
    }.last()

    val visitedTiles = finalState.map.flatten().count { it == TRAVELLED }

    println("Number of tiles the guard has visited: $visitedTiles")
}

enum class Direction(val representation: Char) {
    NORTH('^'),
    EAST('>'),
    SOUTH('v'),
    WEST('<');

    fun ninetyDegreesClockwise(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    companion object {
        fun from(char: Char): Direction? = entries.find { it.representation == char }
    }
}

enum class Tile(val recognizedBy: List<Char>) {
    FREE(listOf('.')),
    OBSTRUCTION(listOf('#')),
    GUARD(Direction.entries.map { it.representation }),
    TRAVELLED(listOf());

    companion object {
        fun from(char: Char): Tile? = entries.find { it.recognizedBy.contains(char) }
    }
}

typealias Map = List<List<Tile>>

data class Guard(
    val coordinate: Coordinate,
    val direction: Direction,
) {
    fun coordinateInFront(): Coordinate = when (direction) {
        NORTH -> Coordinate(coordinate.x.toInt(), coordinate.y.toInt() - 1)
        EAST -> Coordinate(coordinate.x.toInt() + 1, coordinate.y.toInt())
        SOUTH -> Coordinate(coordinate.x.toInt(), coordinate.y.toInt() + 1)
        WEST -> Coordinate(coordinate.x.toInt() - 1, coordinate.y.toInt())
    }
}

data class Lab(
    val map: Map,
    val guard: Guard,
) {
    val guardIsInside = map.flatten().contains(GUARD)

    override fun toString(): String = map.joinToString("\n") { row ->
        row.joinToString("") { tile ->
            when (tile) {
                FREE -> gray("·")
                OBSTRUCTION -> white("▢")
                GUARD -> brightBlue(
                    when (guard.direction) {
                        NORTH -> "⮝"
                        WEST -> "⮜"
                        SOUTH -> "⮟"
                        EAST -> "⮞"
                    }
                )

                TRAVELLED -> brightGreen("•")
            }
        }
    }

    companion object {
        fun fromString(input: String): Lab {
            val charGrid = input.lines().map { it.toCharArray().toList() }
            val map = charGrid.map { row -> row.map { point -> Tile.from(point)!! } }
            val guardCoordinate = charGrid.coordinateOfAny(GUARD.recognizedBy)!!
            val guard = Guard(
                coordinate = guardCoordinate,
                direction = Direction.from(charGrid[guardCoordinate]!!)!!
            )

            return Lab(map, guard)
        }
    }
}

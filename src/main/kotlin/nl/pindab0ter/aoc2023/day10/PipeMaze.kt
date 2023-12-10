package nl.pindab0ter.aoc2023.day10

import nl.pindab0ter.aoc2023.day10.Section.*
import nl.pindab0ter.common.*

fun main() {
    val input = getInput(2023, 10)

    val maze = Maze.from(input)

    print(maze)

    println("Furthest distance from the start: ${furthestDistanceFromStart(maze)}")
}


fun furthestDistanceFromStart(maze: Maze): Int {
    fun List<Pair<Coordinates, Direction>>.coordinates() = map(Pair<Coordinates, Direction>::first)

    tailrec fun calculateDistance(steps: List<Pair<Coordinates, Direction>>, distance: Int): Int = when {
        steps.coordinates().allElementsEqual() && distance > 0 -> distance
        else -> calculateDistance(
            steps = steps.map { (coordinates, origin) ->
                val direction = maze.directionFor(coordinates, origin)

                Coordinates(coordinates.x + direction.dx, coordinates.y + direction.dy) to direction
            },
            distance = distance + 1
        )
    }

    // Start with all directions pointing away from the start
    val initialSteps = maze.start.directions.map { direction ->
        maze.startCoordinates to direction.opposite()
    }

    return calculateDistance(initialSteps, 0)
}
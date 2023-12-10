package nl.pindab0ter.aoc2023.day10

import nl.pindab0ter.aoc2023.day10.Section.*
import nl.pindab0ter.common.*

fun main() {
    val input = getInput(2023, 10)

    val maze = Maze.from(input)

    print(maze)

    println("Furthest distance from the start: ${furthestDistanceFromStart(maze)}")
}

fun print(maze: Maze) = buildString {
    maze.tiles.forEach { row -> appendLine(row.joinToString("") { it.toString() }) }
}.let(::println)


fun furthestDistanceFromStart(maze: Maze): Int {
    fun followPipe(coordinates: Coordinates, origin: Direction): Pair<Coordinates, Direction> {
        val direction = maze
            .getDirections(coordinates)!!
            .minus(origin.opposite())
            .first()

        return Coordinates(coordinates.x + direction.dx, coordinates.y + direction.dy) to direction
    }

    val startTile = maze.tiles.find(Tile::isStart)!!
    var distance = 0

    // Start with all directions pointing away from the start
    var steps = startTile.section!!.directions.map { direction ->
        maze.tiles.coordinatesOfFirst { it == startTile }!! to direction.opposite()
    }

    do {
        steps = steps.map { (coordinates, direction) -> followPipe(coordinates, direction) }
        distance++
    } while (!steps.map(Pair<Coordinates, Direction>::first).allElementsEqual())

    return distance
}

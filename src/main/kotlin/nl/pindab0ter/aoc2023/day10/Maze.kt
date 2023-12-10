package nl.pindab0ter.aoc2023.day10

import nl.pindab0ter.common.Coordinates
import nl.pindab0ter.common.coordinatesOfFirst
import nl.pindab0ter.common.get

data class Maze(val sections: List<List<Section?>>, val startCoordinates: Coordinates) {
    val start: Section
        get() = sections[startCoordinates]!!

    fun directionFor(
        coordinates: Coordinates,
        comingFrom: Direction,
    ) = sections[coordinates.y][coordinates.x]?.directions!!
        .minus(comingFrom.opposite())
        .first()

    override fun toString(): String = buildString {
        sections.forEachIndexed { y, column ->
            column.forEachIndexed { x, section ->
                when {
                    startCoordinates.x == x && startCoordinates.y == y -> append('●')
                    section == null -> append('·')
                    else -> append(section.representation)
                }
            }
            appendLine()
        }
    }

    companion object {
        fun from(input: String): Maze {
            val grid = input.lines().map(String::toList)
            val startCoordinates = grid.coordinatesOfFirst { it == 'S' }!!

            return Maze(sections = grid.mapIndexed { y, row ->
                row.mapIndexed { x, character ->
                    when (character) {
                        'S' -> {
                            val directions = Direction.getDirectionsPointingTo(Coordinates(x, y), grid)
                            Section.from(directions.map(Direction::opposite).toSet())
                        }

                        else -> Section.from(character)
                    }
                }
            }, startCoordinates)
        }
    }
}
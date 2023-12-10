package nl.pindab0ter.aoc2023.day10

import nl.pindab0ter.common.Coordinates

data class Maze(val tiles: List<List<Tile>>) {
    fun getTile(coordinates: Coordinates): Tile = tiles[coordinates.y][coordinates.x]
    fun getSection(coordinates: Coordinates): Section? = getTile(coordinates).section
    fun getDirections(coordinates: Coordinates): Set<Direction>? = getSection(coordinates)?.directions

    companion object {
        fun from(input: String): Maze {
            val grid = input.lines().map(String::toList)

            return Maze(tiles = grid.mapIndexed { y, row ->
                row.mapIndexed { x, character ->
                    when (character) {
                        'S' -> {
                            val directions = Direction.getDirectionsPointingTo(x, y, grid)
                            val section = Section.from(directions.map(Direction::opposite).toSet())

                            Tile(section, true)
                        }

                        else -> Tile(Section.from(character))
                    }
                }
            })
        }
    }
}
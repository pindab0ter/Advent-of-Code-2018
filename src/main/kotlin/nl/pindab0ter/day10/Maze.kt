package nl.pindab0ter.day10

data class Maze(val tiles: List<List<Tile>>) {
    companion object {
        fun from(input: String): Maze {
            val grid = input.lines().map(String::toList)

            return Maze(tiles = grid.mapIndexed { x, row ->
                row.mapIndexed { y, character ->
                    when (character) {
                        'S' -> {
                            val directions = Direction.checkAdjacentTiles(grid, x, y) { it in sections.keys }
                            val section = Pipe.from(directions)

                            Tile(section, true)
                        }

                        else -> Tile(sections[character])
                    }
                }
            })
        }
    }
}
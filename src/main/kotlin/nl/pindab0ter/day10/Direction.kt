package nl.pindab0ter.day10

/**
 * Represents a direction in a 2D grid.
 *
 * @property dx The displacement in the x-axis.
 * @property dy The displacement in the y-axis.
 */
enum class Direction(val dx: Int, val dy: Int) {
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    companion object {
        fun <T> checkAdjacentTiles(grid: List<List<T>>, x: Int, y: Int, test: (T?) -> Boolean): Set<Direction> =
            entries.filter {
                test(grid.getOrNull(x + it.dx)?.getOrNull(y + it.dy))
            }.toSet()
    }
}
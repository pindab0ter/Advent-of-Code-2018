package nl.pindab0ter.aoc2023.day10

/**
 * Represents a direction in a 2D grid.
 *
 * @property dx The displacement in the x-axis.
 * @property dy The displacement in the y-axis.
 */
enum class Direction(val dx: Int, val dy: Int) {
    NORTH(0, -1) {
        override fun opposite() = SOUTH
    },
    EAST(1, 0) {
        override fun opposite() = WEST
    },
    SOUTH(0, 1) {
        override fun opposite() = NORTH
    },
    WEST(-1, 0) {
        override fun opposite() = EAST
    };

    abstract fun opposite(): Direction

    companion object {
        fun getDirectionsPointingTo(
            x: Int,
            y: Int,
            grid: List<List<Char>>,
        ): Set<Direction> = setOfNotNull(
            Section.from(grid.getOrNull(y - 1)?.getOrNull(x))?.directions?.firstOrNull { it == SOUTH },
            Section.from(grid.getOrNull(y)?.getOrNull(x + 1))?.directions?.firstOrNull { it == WEST },
            Section.from(grid.getOrNull(y + 1)?.getOrNull(x))?.directions?.firstOrNull { it == NORTH },
            Section.from(grid.getOrNull(y)?.getOrNull(x - 1))?.directions?.firstOrNull { it == EAST },
        ).also(::println)
    }
}
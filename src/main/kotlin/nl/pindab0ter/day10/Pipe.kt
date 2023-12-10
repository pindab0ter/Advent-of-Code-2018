package nl.pindab0ter.day10

enum class Pipe(val connects: Set<Direction>, val representation: Char) {
    VERTICAL(setOf(Direction.NORTH, Direction.SOUTH), '│'),
    HORIZONTAL(setOf(Direction.EAST, Direction.WEST), '─'),
    TOP_LEFT_BEND(setOf(Direction.SOUTH, Direction.EAST), '╭'),
    TOP_RIGHT_BEND(setOf(Direction.WEST, Direction.SOUTH), '╮'),
    BOTTOM_LEFT_BEND(setOf(Direction.WEST, Direction.NORTH), '╰'),
    BOTTOM_RIGHT_BEND(setOf(Direction.NORTH, Direction.EAST), '╯');

    companion object {
        fun from(directions: Set<Direction>): Pipe = entries.first { it.connects.containsAll(directions) }
    }
}
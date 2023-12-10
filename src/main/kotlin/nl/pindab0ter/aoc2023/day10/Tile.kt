package nl.pindab0ter.aoc2023.day10

data class Tile(val section: Section?, val isStart: Boolean = false) {
    init {
        require(!(section == null && isStart)) { "A tile can't be a start if it is not a pipe section" }
    }

    override fun toString(): String = when (isStart) {
        true -> "●"
        else -> section?.representation?.toString() ?: "·"
    }
}
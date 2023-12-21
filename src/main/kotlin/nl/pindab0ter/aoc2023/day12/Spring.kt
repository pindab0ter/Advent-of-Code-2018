package nl.pindab0ter.aoc2023.day12

enum class Spring(val representation: Char) {
    OPERATIONAL('.'),
    DAMAGED('#'),
    UNKNOWN('?');

    override fun toString(): String {
        return this.representation.toString()
    }

    companion object {
        fun from(character: Char) = entries.first { it.representation == character }
    }
}
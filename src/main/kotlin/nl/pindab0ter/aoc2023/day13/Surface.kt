package nl.pindab0ter.aoc2023.day13

enum class Surface(val representation: Char) {
    ASH('.'),
    ROCK('#');

    override fun toString(): String = representation.toString()

    companion object {
        fun from(character: Char): Surface? = entries.firstOrNull { it.representation == character }
    }
}
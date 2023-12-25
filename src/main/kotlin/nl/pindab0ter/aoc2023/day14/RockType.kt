package nl.pindab0ter.aoc2023.day14

enum class RockType(val representation: Char) {
    SQUARE('#'),
    ROUND('O');

    companion object {
        fun from(character: Char): RockType? = RockType.entries.firstOrNull { it.representation == character }
    }
}
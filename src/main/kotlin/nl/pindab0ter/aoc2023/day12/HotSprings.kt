package nl.pindab0ter.aoc2023.day12

import nl.pindab0ter.aoc2023.day12.Spring.*
import nl.pindab0ter.common.getInput
import nl.pindab0ter.common.println
import nl.pindab0ter.common.timing

fun main() {
    val input = getInput(2023, 12)
    val records = parse(input)

    timing {
        val sumOfArrangements = records.sumOf(Record::countArrangements)
        println("Sum of arrangements: $sumOfArrangements")
    }
}

typealias Group = Int
typealias Groups = List<Group>

data class Record(val springs: List<Spring>, val groups: Groups) {
    fun countArrangements(
        springs: List<Spring> = this.springs,
        groups: Groups = this.groups,
        acc: Long = 0L,
    ): Long {
        if (groups.isEmpty()) return if (DAMAGED in springs) 0 else acc + 1
        if (springs.isEmpty()) return acc

        fun operationalSpring() = countArrangements(springs.drop(1), groups)
        fun damagedSpring(): Long {
            val group = groups.first()
            return if (
                group <= springs.size &&               // Enough springs remain to satisfy group
                OPERATIONAL !in springs.take(group) && // The group doesn't contain operational springs
                (springs.size == group ||              // These are the last remaining springs, or
                        springs[group] != DAMAGED)     // The spring after this group is not damaged
            ) countArrangements(springs.drop(group + 1), groups.drop(1)) else 0
        }

        return when (springs.first()) {
            OPERATIONAL -> operationalSpring()
            DAMAGED -> damagedSpring()
            UNKNOWN -> operationalSpring() + damagedSpring()
        }
    }

    override fun toString(): String = buildString {
        append(springs.joinToString(""))
        append(" ")
        append(groups.joinToString(""))
    }
}

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

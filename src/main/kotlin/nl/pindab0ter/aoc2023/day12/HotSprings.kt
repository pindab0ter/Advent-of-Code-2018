package nl.pindab0ter.aoc2023.day12

import nl.pindab0ter.aoc2023.day12.Spring.OPERATIONAL
import nl.pindab0ter.common.println
import nl.pindab0ter.common.replace

fun main() {
    val input = """
        ???.### 1,1,3
        .??..??...?##. 1,1,3
        ?#?#?#?#?#?#?#? 1,3,1,6
        ????.#...#... 4,1,1
        ????.######..#####. 1,6,5
        ?###???????? 3,2,1
    """.trimIndent()
    val records = parse(input)

    println(records[1])
    println(records[1].countArrangements())
}

data class Record(val springs: List<Spring>, val groups: List<Int>) {
    fun countArrangements(
        acc: Long = 0L,
        index: Int = 0,
        groupIndex: Int = 0,
        groupElementIndex: Int = 0,
    ): Long {
        println(
            "Index: $index, Springs: ${springs.drop(index)}, Groups: ${
                groups.drop(groupIndex).let { group ->
                    group.replace(0, (group.firstOrNull()?.minus(groupElementIndex)))
                }
            }"
        )
        if (index == springs.size) {
            // If there are no more groups left, we have found a valid arrangement.
            return if (
                (groupIndex == groups.size && groupElementIndex == 0) ||
                (groupIndex == groups.size - 1 && groupElementIndex == groups[groupIndex])
            ) 1 else 0
        }

        // If we have reached the end of a group, move to the next group.
        if (groups.getOrNull(groupIndex) == groupElementIndex) return acc + countArrangements(
            acc,
            index + 1,
            groupIndex + 1,
            0
        )

        return acc + when (springs[index]) {
            OPERATIONAL -> countArrangements(acc, index + 1, groupIndex, 0)
            else -> countArrangements(acc, index + 1, groupIndex, groupElementIndex + 1)
        }
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

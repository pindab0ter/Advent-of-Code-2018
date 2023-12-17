package nl.pindab0ter.aoc2023.day12

import com.github.ajalt.mordant.rendering.TextColors.*
import nl.pindab0ter.aoc2023.day12.Spring.*
import nl.pindab0ter.common.getInput
import nl.pindab0ter.common.println
import nl.pindab0ter.common.replaceFirst
import nl.pindab0ter.common.timing

fun main() {
    val input = getInput(2023, 12)
    val records = parse(input)

    timing {
        println(records.map(Record::countArrangements).sum())
    }
}

data class Record(val springs: List<Spring>, val groups: List<Int>) {
//    private val cache = mutableMapOf<???>()

    // TODO: Tailrec?
    fun countArrangements(
        acc: Long = 0L,
        index: Int = 0,
        groupIndex: Int = 0,
        groupElementIndex: Int = 0,
        previousDamaged: Boolean = false,
        output: List<Spring> = emptyList(),
        depth: Int = 0,
    ): Long {
        val indentation = "".repeat(depth)
        val currentGroupSize = groups.getOrNull(groupIndex) ?: 0
        val passedLastIndex = index == springs.size
        val isLastInGroup = groupElementIndex == currentGroupSize - 1
        val passedLastInGroup = groupElementIndex >= currentGroupSize
        val isFirstInGroup = groupElementIndex == 0
        val expectedDamagedSpringsRemaining = groups.drop(groupIndex).sum() - groupElementIndex


        val representation = gray(buildString {
            append("Springs: ")
            append(white("["))
            append(brightWhite(output.joinToString(", ")))
            if (output.isNotEmpty() && springs.drop(index).isNotEmpty()) append(", ")
            append(white(springs.drop(index).joinToString(", ")))
            append(white("]"))
            append(", Groups: ")
            append(
                white(
                    groups.drop(groupIndex).replaceFirst { it - groupElementIndex }.toString()
                        .padStart(groups.toString().length)
                )
            )
        }).prependIndent(indentation)

        if (passedLastIndex) {
            return when {
                // If there are no more groups left, we have found a valid arrangement.
                expectedDamagedSpringsRemaining == 0 -> {
                    println(representation, green("Valid arrangement"))
                    1L
                }

                else -> {
                    println(representation, red("Damaged springs remaining"))
                    0L
                }
            }
        }

        fun nextGroup(nextDepth: Int = depth) = when {
            groupIndex == groups.size -> {
                println(representation, red("Not enough groups"))
                0L
            }

            else -> countArrangements(acc, index + 1, groupIndex + 1, 0, true, output.plus(DAMAGED), nextDepth)
        }

        fun nextDamaged(nextDepth: Int = depth): Long = when {
            isFirstInGroup && previousDamaged -> {
                println(representation, red("Damaged after damaged group"))
                0L
            }

            groupIndex == groups.size -> {
                println(representation, red("Not enough groups"))
                0L
            }

            isLastInGroup -> nextGroup()
            else -> countArrangements(
                acc,
                index + 1,
                groupIndex,
                groupElementIndex + 1,
                true,
                output.plus(DAMAGED),
                nextDepth
            )
        }

        fun next(nextDepth: Int = depth) = when {
            !passedLastInGroup && groupElementIndex > 0 -> {
                println(representation, red("Still in a group"))
                0L
            }

            else -> countArrangements(acc, index + 1, groupIndex, 0, false, output.plus(OPERATIONAL), nextDepth)
        }

        println(representation)

        return acc + when (springs[index]) {
            OPERATIONAL -> next()
            DAMAGED -> nextDamaged()
            UNKNOWN -> nextDamaged(depth + 1) + next()
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

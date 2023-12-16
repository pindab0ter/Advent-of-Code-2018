package nl.pindab0ter.aoc2023.day12

import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.gray
import com.github.ajalt.mordant.rendering.TextColors.white
import nl.pindab0ter.aoc2023.day12.Spring.*
import nl.pindab0ter.common.println
import nl.pindab0ter.common.replaceFirst

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

    println(records.last())
    println(records.last().countArrangements())
}

private const val damagedIndicator = "✗"
private const val operationalIndicator = "○"

data class Record(val springs: List<Spring>, val groups: List<Int>) {
//    private val cache = mutableMapOf<???>()

    // TODO: Tailrec?
    fun countArrangements(
        acc: Long = 0L,
        index: Int = 0,
        groupIndex: Int = 0,
        groupElementIndex: Int = 0,
        previousDamaged: Boolean = false,
        depth: Int = 0,
    ): Long {
        val indentation = "    ".repeat(depth)
        val currentGroupSize = groups.getOrNull(groupIndex) ?: 0
        val passedLastIndex = index == springs.size
        val isLastInGroup = groupElementIndex == currentGroupSize - 1
        val passedLastInGroup = groupElementIndex >= currentGroupSize
        val isFirstInGroup = groupElementIndex == 0
        val expectedDamagedSpringsRemaining = groups.drop(groupIndex).sum() - groupElementIndex


        println(
            gray(buildString {
                append(white(if (previousDamaged) damagedIndicator else operationalIndicator))
                append(" Springs: ")
                append(white(springs.drop(index).toString().padStart(springs.toString().length)))
                append(", Groups: ")
                append(
                    white(
                        groups.drop(groupIndex).replaceFirst { it - groupElementIndex }.toString()
                            .padStart(groups.toString().length)
                    )
                )
            }).prependIndent(indentation)
        )

        if (passedLastIndex) {
            return when {
                // If there are no more groups left, we have found a valid arrangement.
                expectedDamagedSpringsRemaining == 0 -> {
                    println(TextColors.green("Valid arrangement").prependIndent(indentation))
                    1L
                }

                else -> {
                    println(TextColors.red("Damaged springs remaining").prependIndent(indentation))
                    0L
                }
            }
        }

        fun nextGroup(nextDepth: Int = depth) = when {
            groupIndex == groups.size -> {
                println(TextColors.red("Not enough groups").prependIndent(indentation))
                0L
            }

            else -> countArrangements(acc, index + 1, groupIndex + 1, 0, true, nextDepth)
        }

        fun nextDamaged(nextDepth: Int = depth): Long = when {
            isFirstInGroup && previousDamaged -> {
                println(TextColors.red("Damaged after damaged group").prependIndent(indentation))
                0L
            }

            isLastInGroup -> nextGroup()
            else -> countArrangements(acc, index + 1, groupIndex, groupElementIndex + 1, true, nextDepth)
        }

        fun next(nextDepth: Int = depth) = when {
            !passedLastInGroup && groupElementIndex > 0 -> {
                println(TextColors.red("Still in a group").prependIndent(indentation))
                0L
            }

            else -> countArrangements(acc, index + 1, groupIndex, 0, false, nextDepth)
        }

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

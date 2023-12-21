package nl.pindab0ter.aoc2023.day13

import nl.pindab0ter.common.getInput
import nl.pindab0ter.common.println

fun main() {
    val input = getInput(2023, 13)

    val verticalSum = parse(input).sumOf { pattern -> getSymmetryAxisIndex(pattern.columns)?.plus(1) ?: 0 }
    val horizontalSum = parse(input).sumOf { pattern -> getSymmetryAxisIndex(pattern.rows)?.plus(1) ?: 0 }

    val summaryOfAllNotes = verticalSum + (horizontalSum * 100)

    require(summaryOfAllNotes != 29428) { "Known incorrect answer" }

    println("Summary of all notes: $summaryOfAllNotes")
}

fun getSymmetryAxisIndex(pattern: List<List<Surface>>): Int? {
    val reflectionLine = (1 until pattern.size).firstOrNull { y -> pattern[y] == pattern[y - 1] } ?: return null
    val distanceToEdge = minOf(pattern.size - reflectionLine, reflectionLine) + 1

    // Start at the first column past the one we know is symmetrical (index + 2)
    val isPerfectReflection = (2 until distanceToEdge).all { y ->
        pattern[reflectionLine + y - 1] == pattern[reflectionLine - y]
    }

    return if (isPerfectReflection) reflectionLine - 1 else null
}

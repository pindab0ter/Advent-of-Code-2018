package nl.pindab0ter.aoc2024.day04

import nl.pindab0ter.common.getInput
import nl.pindab0ter.common.rotate45Degrees
import nl.pindab0ter.common.rotate45DegreesAntiClockwise
import nl.pindab0ter.common.transpose

fun main() {
    val input = getInput(2024, 4).lines().map(String::toList)

    val xmasCount = input.countXmasOccurrences()

    println("Amount of times XMAS can be found in this word search: $xmasCount")
}

fun List<List<Char>>.countXmasOccurrences(): Int = this
    .plus(this.transpose())
    .plus(this.rotate45Degrees())
    .plus(this.rotate45DegreesAntiClockwise())
    .sumOf { line ->
        line
            .joinToString("")
            .windowed(4)
            .count { it == "XMAS" || it == "SAMX" }
    }

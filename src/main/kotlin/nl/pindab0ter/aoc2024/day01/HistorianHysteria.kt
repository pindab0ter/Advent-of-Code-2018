package nl.pindab0ter.aoc2024.day01

import nl.pindab0ter.common.getInput
import nl.pindab0ter.common.transpose
import kotlin.math.abs

fun main() {
    val input = getInput(2024, 1)

    val result = parse(input)
        .pairSortedNumbers()
        .totalDistance()

    println(result)
}

fun List<Pair<Int, Int>>.totalDistance(): Int = sumOf { it.distance() }

fun Pair<Int, Int>.distance(): Int = abs(first - second)

private fun List<List<Int>>.pairSortedNumbers(): List<Pair<Int, Int>> = transpose()
    .map { it.sorted() }
    .transpose()
    .map { it.elementAt(0) to it.elementAt(1) }

private fun parse(input: String): List<List<Int>> = Regex("""(\d+)\s+(\d+)""")
    .findAll(input)
    .map { matchResult ->
        matchResult.destructured.toList().map<String, Int>(String::toInt)
    }
    .toList()

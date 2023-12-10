package nl.pindab0ter.aoc2023.day09

import nl.pindab0ter.common.getInput

fun main() {
    val sequences = parse(getInput(2023, 9))

    val sumOfExtrapolatedNextValues = sequences.sumOf(::extrapolateValue)
    println("The sum of the extrapolated values is $sumOfExtrapolatedNextValues")

    val sumOfExtrapolatedPreviousValues = sequences.map { it.asReversed() }.sumOf(::extrapolateValue)
    println("The sum of the extrapolated values is $sumOfExtrapolatedPreviousValues")
}

fun parse(input: String) = input.lines().map { line -> line.split(' ').map(String::toInt) }

fun extrapolateValue(sequence: List<Int>): Int {
    val differences = sequence.windowed(2).map { (a, b) -> b - a }

    return when {
        differences.all { it == 0 } -> sequence.last() + differences.first()
        else -> sequence.last() + extrapolateValue(differences)
    }
}
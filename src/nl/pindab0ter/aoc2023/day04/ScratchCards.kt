package nl.pindab0ter.aoc2023.day04

import kotlin.math.pow

fun main() {
    val input = ClassLoader.getSystemResource("2023/day04/input").readText()
    val cards = input.lines().map(::parseLine)

    val totalValue = cards.sumOf(ScratchCard::value)

    println("Combined value of all cards: $totalValue")
}

fun Int.pow(exponent: Int): Int {
    return toDouble().pow(exponent.toDouble()).toInt()
}


data class ScratchCard(
    val number: Int,
    val winningNumbers: Set<Int>,
    val candidateNumbers: Set<Int>,
) {
    fun value(): Int {
        val numbers = candidateNumbers.count { winningNumbers.contains(it) }
        return 2.pow(numbers - 1)
    }
}

val cardRegex = Regex("""^Card\s*(\d+): ([\s\d]+) \| ([\s\d]+)$""")
val spaceRegex = Regex("""\s{1,2}""")

fun parseLine(line: String): ScratchCard {
    val matchResult = cardRegex.find(line)
    val (number, winningNumbers, candidateNumbers) = matchResult!!.destructured
    return ScratchCard(
        number.toInt(),
        winningNumbers.trim().split(spaceRegex).map(String::toInt).toSet(),
        candidateNumbers.trim().split(spaceRegex).map(String::toInt).toSet(),
    )
}
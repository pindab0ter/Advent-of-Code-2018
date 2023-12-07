package nl.pindab0ter.aoc2023.day07


fun main() {
    val input = ClassLoader.getSystemResource("2023/day07/input").readText()
    val hands = parse(input)

    println(hands.totalWinnings())
}

fun parse(input: String): List<Hand> = input.lines().map(Hand::invoke)

fun List<Hand>.totalWinnings(): Int = indices.zip(sorted().reversed()).sumOf { (index, hand) -> hand.bid * (index + 1) }

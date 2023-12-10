package nl.pindab0ter.aoc2023.day07.part2

import nl.pindab0ter.common.getInput


fun main() {
    val hands = parse(getInput(2023, 7))

    println(hands.totalWinnings())
}

fun parse(input: String): List<Hand> = input.lines().map(Hand.Companion::invoke)

fun List<Hand>.totalWinnings(): Int = indices.zip(sorted().reversed()).sumOf { (index, hand) -> hand.bid * (index + 1) }

package nl.pindab0ter.aoc2015.day01

import nl.pindab0ter.common.getInput
import nl.pindab0ter.common.println

fun main() {
    val input = getInput(2015, 1)

    val floor = input.sumOf { character ->
        if (character == '(') 1 as Int
        else -1
    }

    println(floor)
}

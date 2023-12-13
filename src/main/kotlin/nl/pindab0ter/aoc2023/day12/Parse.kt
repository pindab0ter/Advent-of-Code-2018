package nl.pindab0ter.aoc2023.day12

fun parse(input: String): List<Record> = input.lines().map { line ->
    val (springs, damagedSprings) = line.split(" ")

    Record(
        springs = springs.map(Spring::from),
        groups = damagedSprings.split(',').map(String::toInt)
    )
}
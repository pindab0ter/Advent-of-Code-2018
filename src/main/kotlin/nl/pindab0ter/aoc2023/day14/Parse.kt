package nl.pindab0ter.aoc2023.day14

fun parse(input: String): Set<Rock> = input
    .lines()
    .flatMapIndexed { y, line ->
        line.mapIndexed { x, character ->
            val type = RockType.from(character)
            if (type == null) null
            else Rock(x, y, type)
        }.filterNotNull()
    }.toSet()
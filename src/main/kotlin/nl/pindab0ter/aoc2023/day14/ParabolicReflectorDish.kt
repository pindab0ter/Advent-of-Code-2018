package nl.pindab0ter.aoc2023.day14

import nl.pindab0ter.aoc2023.day14.RockType.ROUND
import nl.pindab0ter.common.println

fun main() {
    val input = """
        OOOO.#.O..
        OO..#....#
        OO..O##..O
        O..#.OO...
        ........#.
        ..#....#.#
        ..O..#.O.O
        ..O.......
        #....###..
        #....#....
    """.trimIndent()

    val rocks = parse(input)

    println(calculateLoad(rocks))
}

fun calculateLoad(rocks: Set<Rock>): Int {
    val roundRocks = rocks.filter { it.type == ROUND }.toSet()
    fun Rock.weight(): Int = rocks.maxOf(Rock::y) + 1 - y

    tailrec fun weightOf(rocks: Set<Rock>, rock: Rock, acc: Int = 0): Int {
        val remainingRocks = rocks.minus(rock)
        val rockBelow = rocks.firstOrNull { it.y == rock.y + 1 }

        if (remainingRocks.isEmpty()) return acc + rock.weight()

        if (rockBelow != null) return weightOf(
            rocks = remainingRocks.minus(rockBelow),
            rock = rockBelow,
            acc = acc + rock.weight()
        )

        return weightOf(
            rocks = remainingRocks,
            rock = remainingRocks.first(),
            acc = acc + rock.weight()
        )
    }

    return weightOf(roundRocks, roundRocks.first())
}

package nl.pindab0ter.aoc2023.day14

import nl.pindab0ter.aoc2023.day14.RockType.ROUND
import nl.pindab0ter.aoc2023.day14.RockType.SQUARE
import nl.pindab0ter.common.getInput
import nl.pindab0ter.common.println

fun main() {
    val input = getInput(2023, 14)

    val rocks = parse(input)
    val tiltedRocks = tilt(rocks)


    println("The total load on the north support beams after tilting: ${calculateLoad(tiltedRocks)}")
}

fun tilt(rocks: Set<Rock>): Set<Rock> = rocks.fold(emptySet<Rock>()) { acc, rock ->
    when (rock.type) {
        SQUARE -> acc + rock
        ROUND -> {
            val rockAbove = acc.lastOrNull { it.x == rock.x && it.y < rock.y }
            acc + rock.copy(y = rockAbove?.y?.plus(1) ?: 0)
        }
    }
}.toSet()

fun calculateLoad(rocks: Set<Rock>): Int {
    val roundRocks = rocks.filter { it.type == ROUND }.toSet()
    fun Rock.weight(): Int = rocks.maxOf(Rock::y) + 1 - y

    /**
     * The [rocks] need to be sorted by `x`, then `y`
     */
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

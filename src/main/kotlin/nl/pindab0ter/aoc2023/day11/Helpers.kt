package nl.pindab0ter.aoc2023.day11

import nl.pindab0ter.common.Coordinate
import nl.pindab0ter.common.manhattanDistance
import nl.pindab0ter.common.tail
import kotlin.math.max

fun Set<Coordinate>.sumOfDistancesBetweenGalaxies(expansionMultiplier: Long = 1L): Long =
    expand(expansionMultiplier).distancesBetween().sum()

fun Set<Coordinate>.distancesBetween() = allCombinations().map { (a, b) -> manhattanDistance(a, b) }

fun <T> Set<T>.allCombinations(): Set<Pair<T, T>> = withIndex().flatMap { (i, a) ->
    drop(i + 1).map { b -> a to b }
}.toSet()

fun Set<Coordinate>.expand(multiplier: Long = 1): Set<Coordinate> {
    val emptyRows = (minOf(Coordinate::x)..(maxOf(Coordinate::x)))
        .filter { y -> none { it.y == y } }.toSet().contiguousToRanges()
    val emptyColumns = (minOf(Coordinate::y)..(maxOf(Coordinate::y)))
        .filter { x -> none { it.x == x } }.toSet().contiguousToRanges()

    return map { galaxy ->
        galaxy.copy(
            x = galaxy.x + (emptyColumns.filter { it.last < galaxy.x }.sumOf(LongRange::count) * max(
                1L,
                multiplier - 1L
            )),
            y = galaxy.y + (emptyRows.filter { it.last < galaxy.y }.sumOf(LongRange::count) * max(1L, multiplier - 1L)),
        )
    }.toSet()
}

fun Set<Long>.contiguousToRanges(): Set<LongRange> = tail().fold(listOf(LongRange(first(), first()))) { acc, x ->
    val currentRange: LongRange = acc.last()
    if (currentRange.last + 1L == x) acc.dropLast(1).plusElement(LongRange(currentRange.first, x))
    else acc.plusElement(x..x)
}.toSet()
